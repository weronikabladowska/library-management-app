package pl.sda.librarymanagementapp.model.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sda.librarymanagementapp.domain.book.Book;
import pl.sda.librarymanagementapp.exception.BadRequestException;
import pl.sda.librarymanagementapp.exception.DatabaseSavingErrorException;
import pl.sda.librarymanagementapp.exception.NotFoundException;
import pl.sda.librarymanagementapp.model.mapper.BookMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final RestTemplate restTemplate;
    private final BookMapper bookMapper;

    public List<BookDto> findBookByTitle(String title) {

        ResponseEntity<BookSourceResponse> entity = restTemplate.getForEntity(createURLwithTitle(title), BookSourceResponse.class);

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

    public List<BookDto> findBookByAuthor(String author) {

        ResponseEntity<BookSourceResponse> entity = restTemplate.getForEntity(createURLwithAuthor(author), BookSourceResponse.class);

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
    public String createURLwithTitle(String title) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("data.bn.org.pl/api/bibs.json")
                .queryParam("title", title)
                .build()
                .toUriString();
        return url;
    }


    public String createURLwithAuthor(String author) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("data.bn.org.pl/api/bibs.json")
                .queryParam("author", author)
                .build()
                .toUriString();
        return url;
    }
}
