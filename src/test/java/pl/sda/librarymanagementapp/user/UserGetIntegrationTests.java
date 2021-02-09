package pl.sda.librarymanagementapp.user;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class UserGetIntegrationTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void init() {
        userRepository.deleteAll();
        userRepository.save(createUser());
        userRepository.save(createUser());
    }

    @Test
    void getById_getCorrectUser() throws Exception {
        //given

        LibraryUser user = userRepository.save(createUser());
        Long id = user.getId();
        MockHttpServletRequestBuilder request = get("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        UserDto userDtoFromResponse = objectMapper.readValue(responseBody, UserDto.class);
        assertThat(userDtoFromResponse).isNotNull();
        assertThat(userDtoFromResponse.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDtoFromResponse.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userDtoFromResponse.getLastName()).isEqualTo(user.getLastName());
        assertThat(userDtoFromResponse.getRole()).isEqualTo(user.getRole());
        assertThat(userDtoFromResponse.getTel()).isEqualTo(user.getTel());
        assertThat(userDtoFromResponse.getYear()).isEqualTo(user.getYear());
    }


    private LibraryUser createUser() {
        return new LibraryUser().builder()
                .tel(562147896L)
                .email("email@Gmail.com")
                .firstName("Kasia")
                .role(Role.USER)
                .year(1993)
                .lastName("Wnuk")
                .build();
    }


}
