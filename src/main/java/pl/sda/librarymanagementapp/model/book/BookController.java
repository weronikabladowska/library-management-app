package pl.sda.librarymanagementapp.model.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.librarymanagementapp.domain.book.Book;
import pl.sda.librarymanagementapp.exception.BadRequestException;
import pl.sda.librarymanagementapp.model.mapper.BookMapper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@Validated
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
    public Page<BookDto> getPaginatedBooksbyAuthor(
            @RequestParam(name = "author") String author,
            @RequestParam(name = "page") Optional<Integer> page,
            @RequestParam(name = "size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<BookDto> bookPage = bookService.findPaginatedbyAuthor(PageRequest.of(currentPage - 1, pageSize), author);
        return bookPage;

    }


    @GetMapping(value = "/booksPage", params = "title")
    public Page<BookDto> getPaginatedBooksbyTitle(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "page") Optional<Integer> page,
            @RequestParam(name = "size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<BookDto> bookPage = bookService.findPaginatedbyTitle(PageRequest.of(currentPage - 1, pageSize), title);

        return bookPage;
    }
}
