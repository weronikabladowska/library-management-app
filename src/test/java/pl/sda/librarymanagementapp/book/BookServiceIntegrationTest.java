package pl.sda.librarymanagementapp.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void findBookByTitle_returnsDetailsOfBook() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get("/books?title=hobbit").contentType(MediaType.APPLICATION_JSON);
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());


    }

    @Test
    void findBookByAuthor_returnsDetailsOfBook() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get("/books?author=tolkien").contentType(MediaType.APPLICATION_JSON);
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());


    }

    @Test
    void findPaginatedbyAuthor_returnsDetailsofBook() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get("/booksPage?author=tolkien").contentType(MediaType.APPLICATION_JSON);
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void findPaginatedbyTitle_returnsDetailsofBook() throws Exception {
        //given
        MockHttpServletRequestBuilder request = get("/booksPage?title=hobbit").contentType(MediaType.APPLICATION_JSON);
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}
