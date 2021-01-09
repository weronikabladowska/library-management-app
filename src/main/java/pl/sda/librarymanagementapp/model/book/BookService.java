package pl.sda.librarymanagementapp.model.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sda.librarymanagementapp.exception.BadRequestException;
import pl.sda.librarymanagementapp.model.mapper.BookMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final RestTemplate restTemplate;
    private final BookMapper bookMapper;
    private final String scheme = "https";
    private final String host = "data.bn.org.pl/api/bibs.json";
    private final String queryTitle = "title";
    private final String queryAuthor = "author";

    public List<BookDto> findBookByTitle(String title) {
        ResponseEntity<BookSourceResponse> entity = fetchResults(createURLWithTitle(title));
        return createBooksList(entity);
    }

    public List<BookDto> findBookByAuthor(String author) {
        ResponseEntity<BookSourceResponse> entity = fetchResults(createURLWithAuthor(author));
        return createBooksList(entity);
    }

    public Page<BookDto> findPaginatedByAuthor(Pageable pageable, String author) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BookDto> list;
        List<BookDto> books = findBookByAuthor(author);

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }
        return new PageImpl<BookDto>(list, PageRequest.of(currentPage, pageSize), books.size());
    }

    //
    public Page<BookDto> findPaginatedByTitle(Pageable pageable, String title) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BookDto> list;
        List<BookDto> books = findBookByTitle(title);

        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }
        return new PageImpl<BookDto>(list, PageRequest.of(currentPage, pageSize), books.size());
    }

    //    data.bn.org.pl/docs/bibs
//    /api/bibs.json?limit=20&sinceId=2
    public String createURLWithTitle(String title) {
        String url = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .queryParam(queryTitle, title)
                .build()
                .toUriString();
        return url;
    }


    public String createURLWithAuthor(String author) {
        String url = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .queryParam(queryAuthor, author)
                .build()
                .toUriString();
        return url;
    }

    public List<BookDto> addAllBooksFromResponse(BookSourceResponse bookSourceResponse) {
        return bookSourceResponse.getBibs()
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }


    public ResponseEntity<BookSourceResponse> fetchResults(String url) {
        return restTemplate.getForEntity(url, BookSourceResponse.class);
            }

    List<BookDto> createBooksList(ResponseEntity<BookSourceResponse> entity) {
        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new BadRequestException("Error in getting the data from external service");
        }
        BookSourceResponse response = entity.getBody();

        if (response != null) {
            List<BookDto> books = addAllBooksFromResponse(response);

            while (!response.getNextPage().isBlank()) {
                fetchResults(response.getNextPage());
                response = fetchResults(response.getNextPage()).getBody();
                books.addAll(addAllBooksFromResponse(response));
            }
            return books;
        } else {
            throw new BadRequestException("Cannot find the book in the database.");
        }
    }
}
