package pl.sda.librarymanagementapp.model.addres;


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
import pl.sda.librarymanagementapp.adress.AddressDto;
import pl.sda.librarymanagementapp.adress.AdressRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressCreateIntegrationTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AdressRepository adressRepository;

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void createNewAddress_getStatus201Created() throws Exception {
        //given
        adressRepository.deleteAll();
        AddressDto addressDTO = new AddressDto(null, "Sopot", "Grunwaldzka", "11/34", "81-736");
        String requestBody = objectMapper.writeValueAsString(addressDTO);
        MockHttpServletRequestBuilder request = post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    void createNewAddress_whenPostCodeIsNull_getStatus201() throws Exception {
        //given
        adressRepository.deleteAll();
        AddressDto addressDTO = new AddressDto(null, "Sopot", "Grunwaldzka", "11/34", "81-736");
        String requestBody = objectMapper.writeValueAsString(addressDTO);
        MockHttpServletRequestBuilder request = post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void createUser_whenCityIsEmpty_returns400StatusCode() throws Exception {
        //given
        adressRepository.deleteAll();
        AddressDto addressDTO = new AddressDto(null, "", "Grunwaldzka", "11/34", "81-736");
        String requestBody = objectMapper.writeValueAsString(addressDTO);
        MockHttpServletRequestBuilder request = post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createUser_whenStreetIsEmpty_returns400StatusCode() throws Exception {
        //given
        adressRepository.deleteAll();
        AddressDto addressDTO = new AddressDto(null, "Gdańsk", "", "11/34", "81-736");
        String requestBody = objectMapper.writeValueAsString(addressDTO);
        MockHttpServletRequestBuilder request = post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createUser_whenHouseNumberIsEmpty_returns400StatusCode() throws Exception {
        //given
        adressRepository.deleteAll();
        AddressDto addressDTO = new AddressDto(null, "Gdańsk", "Wesoła", "", "81-736");
        String requestBody = objectMapper.writeValueAsString(addressDTO);
        MockHttpServletRequestBuilder request = post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}
