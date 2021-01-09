package pl.sda.librarymanagementapp.book;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService;




}
