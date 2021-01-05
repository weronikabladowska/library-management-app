package pl.sda.librarymanagementapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.sda.librarymanagementapp.domain.book.Book;
import pl.sda.librarymanagementapp.model.book.BookDto;
import pl.sda.librarymanagementapp.model.book.SingleBook;
import pl.sda.librarymanagementapp.model.mapper.BookMapper;

@Configuration
public class ConfigRestTemplate {

    @Bean
    public RestTemplate confgurationRestTemplate() {
        return new RestTemplate();
    }

}
