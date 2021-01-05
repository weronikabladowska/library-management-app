package pl.sda.librarymanagementapp.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.sda.librarymanagementapp.domain.book.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class BookServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void findBookByTitle_returnsDetailsOfBook() throws Exception {

        //given
        Book book = new Book();
        book.setTitle("hobbit");
        MockHttpServletRequestBuilder request = get("/book/" + book.getTitle());

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());


    }
}
