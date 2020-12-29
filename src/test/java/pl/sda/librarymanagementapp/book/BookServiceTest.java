package pl.sda.librarymanagementapp.book;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.librarymanagementapp.model.book.BookRepository;
import pl.sda.librarymanagementapp.model.book.BookService;

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
