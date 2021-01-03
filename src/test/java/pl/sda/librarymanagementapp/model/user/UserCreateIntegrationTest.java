package pl.sda.librarymanagementapp.model.user;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import pl.sda.librarymanagementapp.domain.user.Role;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class UserCreateIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createNewUser_getStatus201() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("Marta", "Nowak", 1999, "meil@gmail.com", 589745632L, Role.USER);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void createNewUser_whenYearIsNull_getStatus201() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("Marta", "Nowak", null, "meil@gmail.com", 589745632L, Role.USER);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
    @Test
    void createNewUser_whenPhoneNumberIsNull_getStatus201() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("Marta", "Nowak", 1999, "meil@gmail.com", null, Role.USER);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void createUser_whenNameIsEmpty_returns400StatusCode() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("", "Nowak", 1999, "meil@gmail.com", 589745632L, Role.USER);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder post = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createUser_whenNameIsBlank_returns400StatusCode() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("   ", "Nowak", 1999, "meil@gmail.com", 589745632L, Role.USER);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder post = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createUser_whenLastNameIsEmpty_returns400StatusCode() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("Kasia", "", 1999, "meil@gmail.com", 589745632L, Role.USER);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder post = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createUser_whenLastNameIsBlank_returns400StatusCode() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("Zocha", "   ", 1999, "meil@gmail.com", 589745632L, Role.USER);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder post = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createUser_whenEmailIsEmpty_returns400StatusCode() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("Tomek", "Nowak", 1999, "", 589745632L, Role.USER);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder post = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createUser_whenEmailIsBlank_returns400StatusCode() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("Franek", "Nowak", 1999, "    ", 589745632L, Role.USER);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder post = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createUser_whenRoleIsEmpty_returns400StatusCode() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("Dawid", "Nowak", 1999, "meil@gmail.com", 589745632L, null);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder post = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createUser_whenEmailIsNull_returns400StatusCode() throws Exception {
        //given
        userRepository.deleteAll();
        UserDTO userDTO = new UserDTO("Dawid", "Nowak", 1999, null, 589745632L, Role.USER);
        String requestBody = objectMapper.writeValueAsString(userDTO);
        MockHttpServletRequestBuilder post = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }





}
