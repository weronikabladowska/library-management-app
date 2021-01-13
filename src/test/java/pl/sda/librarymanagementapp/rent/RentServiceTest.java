package pl.sda.librarymanagementapp.rent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.sda.librarymanagementapp.user.LibraryUser;
import pl.sda.librarymanagementapp.user.Role;
import pl.sda.librarymanagementapp.user.UserDto;
import pl.sda.librarymanagementapp.user.UserRepository;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class RentServiceTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    RentRepository rentRepository;

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
        assertThat(rentDtoFromResponse.getLibraryUserId()).isEqualTo(rent.getLibraryUser().getId());
        assertThat(rentDtoFromResponse.getBookId()).isEqualTo(rent.getBookId());
        assertThat(rentDtoFromResponse.getId()).isEqualTo(rent.getId());

    }

    @Test
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
        List<RentDto> rentDtoFromResponse = objectMapper.readValue(responseBody, new TypeReference<>() {});

        rentDtoFromResponse.forEach((rentDto -> assertThat(rentDto.getBookId()).isEqualTo(rent.getBookId())));
    }


    @Test
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
        List<RentDto> rentDtoFromResponse = objectMapper.readValue(responseBody, new TypeReference<>() {});

        rentDtoFromResponse.forEach((rentDto -> assertThat(rentDto.getLibraryUserId()).isEqualTo(id)));

        //todo correct returned null
    }

    @Test
    void findActiveRents_returnsRentList() throws Exception {
        //given
        Rent rent = rentRepository.save(createRent());
        MockHttpServletRequestBuilder request = get("/rents/active")
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        List<RentDto> rentDtoFromResponse = objectMapper.readValue(responseBody, new TypeReference<>() {});

        rentDtoFromResponse.forEach((rentDto -> assertThat(rentDto.isActive())));
    }

    @Test
    void findDelayedRents() throws Exception {
        Rent rent = rentRepository.save(createRent());
        rent.setActive(false);
        MockHttpServletRequestBuilder request = get("/rents/delayed")
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        List<RentDto> rentDtoFromResponse = objectMapper.readValue(responseBody, new TypeReference<>() {});

        rentDtoFromResponse.forEach((rentDto -> assertThat(!rentDto.isActive())));
    }

    @Test
    void createRent_returns201status() {
    }

//    @Test
//    void returnBook_makesRentInactive() throws Exception {
//
//        Rent rent = rentRepository.save(createRent());
//        String requestBody = objectMapper.writeValueAsString(rent);
//        MockHttpServletRequestBuilder request = put("/rents/return")
//                .contentType(MediaType.APPLICATION_JSON).contentType(requestBody);
//
//        //when
//        MvcResult result = mockMvc.perform(request).andExpect();
//
//        //then
//        MockHttpServletResponse response = result.getResponse();
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
//        assertThat(rent.isActive()).isFalse();
//    }


    private Rent createRent() {
        return new Rent.RentBuilder()
                .bookId(1000562L)
                .libraryUser(new LibraryUser())
                .active(true)
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now().plusMonths(1)).build();
    }

}
