package pl.sda.librarymanagementapp.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import pl.sda.librarymanagementapp.Book.Book;
import pl.sda.librarymanagementapp.Book.BookRepository;
import pl.sda.librarymanagementapp.Book.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService;
//    @Spy
//    ObjectMapper objectMapper;
//    @Spy
//    RestTemplate restTemplate;


}
