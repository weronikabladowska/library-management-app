package pl.sda.librarymanagementapp.model.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sda.librarymanagementapp.domain.book.Book;
import pl.sda.librarymanagementapp.exception.BadRequestException;
import pl.sda.librarymanagementapp.exception.DatabaseSavingErrorException;
import pl.sda.librarymanagementapp.exception.NotFoundException;
import pl.sda.librarymanagementapp.model.mapper.BookMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookService {


    private final RestTemplate restTemplate;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;


    public List<BookDto> findBookByTitle(String title) {
//todo stworzyc strone odpowiedzi
        ResponseEntity<BookSourceResponse> entity = restTemplate.getForEntity(createURL(title), BookSourceResponse.class);

        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new BadRequestException("Cannot get the data from external service");
        }
        BookSourceResponse response = entity.getBody();

        List<BookDto> books = response.getBibs()
                .stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());

        return books;
    }

    //    data.bn.org.pl/docs/bibs
//    /api/bibs.json?limit=20&sinceId=2
    public String createURL(String title) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("data.bn.org.pl/api/bibs.json")
                .queryParam("title", title)
                .build()
                .toUriString();
        return url;
    }
}
