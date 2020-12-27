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
import pl.sda.librarymanagementapp.domain.user.Library_user;
import pl.sda.librarymanagementapp.domain.user.Role;
import pl.sda.librarymanagementapp.model.user.UserDTO;
import pl.sda.librarymanagementapp.model.user.UserRepository;
import pl.sda.librarymanagementapp.model.user.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class UserGetIntegrationTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    UserService userService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getById_getCorrectUser() throws Exception {
        //given
        userRepository.deleteAll();
        userRepository.save(createUser());
        userRepository.save(createUser());
        Library_user user = userRepository.save(createUser());
        Long id = user.getId();
        MockHttpServletRequestBuilder request = get("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        UserDTO userDTO = objectMapper.readValue(responseBody, UserDTO.class);
        assertThat(userDTO).isNotNull();
    }

    private Library_user createUser() {
        return new Library_user().builder()
                .tel(562147896L)
                .email("email@Gmail.com")
                .firstName("Kaśka")
                .role(Role.USER)
                .year(1993)
                .lastName("Wnuk")
                .build();
    }


}
