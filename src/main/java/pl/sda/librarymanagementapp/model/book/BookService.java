package pl.sda.librarymanagementapp.model.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.sda.librarymanagementapp.domain.Book;
import pl.sda.librarymanagementapp.exception.BadRequestException;
import pl.sda.librarymanagementapp.exception.DatabaseSavingErrorException;
import pl.sda.librarymanagementapp.exception.NotFoundException;

@Component
@RequiredArgsConstructor
public class BookService {
//    data.bn.org.pl/docs/bibs
//    /api/bibs.json?limit=20&sinceId=2

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final BookRepository bookRepository;


    public Book getBook(String title) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("data.bn.org.pl/api/bibs.json")
                .queryParam("title", title)
                .build()
                .toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);

        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new BadRequestException("Cannot get the data from external service");
        }

        String response = entity.getBody();

        try {
            BookSourceResponse singleBook = objectMapper.readValue(response, BookSourceResponse.class);

            BookSourceResponse.SingleBook singleBook1 = singleBook.getBibs()
                    .stream()
                    .filter(singleBook2 -> singleBook2.getTitle().equals(title))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Cannot find the book with title: " + title));

            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(singleBook1.getAuthor());
            book.setPublisher(singleBook1.getPublisher());
            book.setPublicationYear(singleBook1.getPublicationYear());
            book.setPlaceOfPublication(singleBook1.getPlaceOfPublication());

            Book savedBook = bookRepository.save(book);
            return savedBook;


        } catch (JsonProcessingException e) {
            e.printStackTrace();
            new DatabaseSavingErrorException("Cannot save book to database");
            return null;


        }
    }
}
