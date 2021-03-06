package pl.sda.librarymanagementapp.rent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.Base64Utils;
import pl.sda.librarymanagementapp.user.LibraryUser;
import pl.sda.librarymanagementapp.user.LibraryUserAdapter;
import pl.sda.librarymanagementapp.user.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class RentServiceTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    RentRepository rentRepository;
    @Autowired
    RentMapper rentMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {
        rentRepository.deleteAll();
        rentRepository.save(createRent());
        rentRepository.save(createRent());
    }

    @PostConstruct
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @WithMockUser(value = "admin@admin.com", password = "admin123")
    void findRentById_returnCorrectRent() throws Exception {
        //given
        Rent rent = rentRepository.save(createRent());
        Long id = rent.getId();
        MockHttpServletRequestBuilder request = get("/rents?rentId=" + id)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        RentDto rentDtoFromResponse = objectMapper.readValue(responseBody, RentDto.class);

        assertThat(rentDtoFromResponse).isNotNull();
        assertThat(rentDtoFromResponse.getBookId()).isEqualTo(rent.getBookId());
        assertThat(rentDtoFromResponse.getId()).isEqualTo(rent.getId());

    }

    @Test
    @WithMockUser(value = "admin@admin.com", password = "admin123")
    void findRentByBookId_returnsRentList() throws Exception {

        //given
        Rent rent = rentRepository.save(createRent());
        Long id = rent.getBookId();
        MockHttpServletRequestBuilder request = get("/rents?bookId=" + id)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        List<RentDto> rentDtoFromResponse = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        rentDtoFromResponse.forEach((rentDto -> assertThat(rentDto.getBookId()).isEqualTo(rent.getBookId())));
    }


    @Test
    @WithMockUser(value = "email@email.com", password = "password123")
    void findRentByLibraryUserId_returnsRentList() throws Exception {
        //given
        Rent rent = rentRepository.save(createRent());
        Long id = rent.getLibraryUser().getId();
        MockHttpServletRequestBuilder request = get("/rents?libraryUser=" + id)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        List<RentDto> rentDtoFromResponse = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        rentDtoFromResponse.stream()
                .forEach(rentDto -> assertThat(rentRepository
                        .findRentById(rentDto.getId())
                        .getLibraryUser()
                        .getId())
                        .isEqualTo(id));
    }

    @Test
    void findActiveRents_returnsRentList() throws Exception {
        //given
        Rent rent = rentRepository.save(createRent());
        MockHttpServletRequestBuilder request = get("/rents/active")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString("admin@admin.com:admin123".getBytes()));;

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        List<RentDto> rentDtoFromResponse = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        rentDtoFromResponse.forEach((rentDto -> assertThat(rentDto.isActive())));
    }

    @Test
    void findDelayedRents() throws Exception {
        Rent rent = rentRepository.save(createRent());
        rent.setActive(false);
        MockHttpServletRequestBuilder request = get("/rents/delayed")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString("admin@admin.com:admin123".getBytes()));

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        List<RentDto> rentDtoFromResponse = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        rentDtoFromResponse.forEach((rentDto -> assertThat(!rentDto.isActive())));
    }

    @Test
    void createRent_returns201status() throws Exception {

        //given
        rentRepository.deleteAll();
        RentDto rentDto = new RentDto();
        rentDto.setBookId(1000563L);

        String requestBody = objectMapper.writeValueAsString(rentDto);

        MockHttpServletRequestBuilder request = post("/rents/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString("email@email.com:password123".getBytes()));
        //when
        MvcResult result = mockMvc.perform(request).andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @WithMockUser(value = "email@email.com", password = "password123")
    void returnBook_makesRentInactive() throws Exception {

        Rent rent = rentRepository.save(createRent());
        Long rentId = rent.getId();

        String requestBody = objectMapper.writeValueAsString(rentId);
        MockHttpServletRequestBuilder request = put("/rents/return?rentId=" + rentId)
                .contentType(MediaType.APPLICATION_JSON).contentType(requestBody);
        //when
        mockMvc.perform(request).andExpect(status().isNoContent());
    }


    private Rent createRent() {
        return new Rent.RentBuilder()
                .bookId(1000562L)
                .libraryUser(new LibraryUser())
                .active(true)
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now().plusMonths(1)).build();
    }

}
