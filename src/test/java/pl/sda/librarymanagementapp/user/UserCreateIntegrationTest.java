package pl.sda.librarymanagementapp.user;

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
        LibraryUserModel userModel = new LibraryUserModel("Marta", "Nowak", 1995, "meil@gmail.com", 589745632L, "maslo");
        String requestBody = objectMapper.writeValueAsString(userModel);
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
        LibraryUserModel userModel = new LibraryUserModel("Marta", "Nowak", null, "meil@gmail.com", 589745632L, "maslo");
        String requestBody = objectMapper.writeValueAsString(userModel);
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
        LibraryUserModel userModel = new LibraryUserModel("Marta", "Nowak", 1992, "meil@gmail.com", null, "maslo");
        String requestBody = objectMapper.writeValueAsString(userModel);
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
        LibraryUserModel userModel = new LibraryUserModel("", "Nowak", 1992, "meil@gmail.com", 589745632L, "maslo");
        String requestBody = objectMapper.writeValueAsString(userModel);
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
        LibraryUserModel userModel = new LibraryUserModel("      ", "Nowak", 1992, "meil@gmail.com", 589745632L, "maslo");
        String requestBody = objectMapper.writeValueAsString(userModel);
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
        LibraryUserModel userModel = new LibraryUserModel("Marta", "", 1992, "meil@gmail.com", 589745632L, "maslo");
        String requestBody = objectMapper.writeValueAsString(userModel);
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
        LibraryUserModel userModel = new LibraryUserModel("Marta", "    ", 1990, "meil@gmail.com", 589745632L, "maslo");
        String requestBody = objectMapper.writeValueAsString(userModel);
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
        LibraryUserModel userModel = new LibraryUserModel("Marta", "Nowak", 19985, "", 589745632L, "maslo");
        String requestBody = objectMapper.writeValueAsString(userModel);
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
        LibraryUserModel userModel = new LibraryUserModel("Marta", "Nowak", 1990, "     ", 589745632L, "maslo");
        String requestBody = objectMapper.writeValueAsString(userModel);
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
        LibraryUserModel userModel = new LibraryUserModel("Marta", "Nowak", 1990, null, 589745632L, "maslo");
        String requestBody = objectMapper.writeValueAsString(userModel);
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
