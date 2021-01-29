package pl.sda.librarymanagementapp.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:4200")

public class BookController {

    private final BookService bookService;
    @GetMapping(value = "/books", params = "title")
    public List<BookDto> getBookByTitle(@RequestParam(name = "title") String title) {
        return bookService.findBookByTitle(title);
    }

    @GetMapping(value = "/books", params = "author")
    public List<BookDto> getBookByAuthor(@RequestParam(name = "author") String author) {
        return bookService.findBookByAuthor(author);
    }

    @GetMapping(value = "/booksPage", params = "author")
    public Page<BookDto> getPaginatedBooksbyAuthor(@RequestParam(name = "author") String author) {
        return bookService.findPaginatedByAuthor(PageRequest.of(0, 10), author);
    }


    @GetMapping(value = "/booksPage", params = "title")
    public Page<BookDto> getPaginatedBooksbyTitle(@RequestParam(name = "title") String title) {
        return bookService.findPaginatedByTitle(PageRequest.of(0,10), title);
    }
}
