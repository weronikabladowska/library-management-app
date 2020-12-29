package pl.sda.librarymanagementapp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.sda.librarymanagementapp.domain.Book;
import pl.sda.librarymanagementapp.model.book.BookDto;
import pl.sda.librarymanagementapp.model.mapper.BookMapper;

@Configuration
public class Config {

    @Bean
    public RestTemplate getRestTemplate(){return new RestTemplate();}

    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return objectMapper;
    }

    @Bean
    public BookMapper getBookMapper() {
        return new BookMapper() {
            @Override
            public Book toBook(BookDto bookDto) {
                return null;
            }

            @Override
            public BookDto toBookDto(Book book) {
                return null;
            }
        };
    }
}
