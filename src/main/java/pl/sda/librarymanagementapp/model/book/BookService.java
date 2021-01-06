package pl.sda.librarymanagementapp.model.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import java.util.Collections;
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

        while(!response.getNextPage().isBlank()) {
            entity = restTemplate.getForEntity(response.getNextPage(), BookSourceResponse.class);
            response = entity.getBody();
            books.addAll(response.getBibs()
                    .stream()
                    .map(bookMapper::toBookDto)
                    .collect(Collectors.toList()));
        }
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

        while(!response.getNextPage().isBlank()) {
            entity = restTemplate.getForEntity(response.getNextPage(), BookSourceResponse.class);
            response = entity.getBody();
            books.addAll(response.getBibs()
                    .stream()
                    .map(bookMapper::toBookDto)
                    .collect(Collectors.toList()));
        }
        return books;
    }

//    public Page<BookDto> findPaginatedbyAuthor(Pageable pageable, String author) {
//        int pageSize = pageable.getPageSize();
//        int currentPage=pageable.getPageNumber();
//        int startItem = currentPage * pageSize;
//        List<BookDto>list;
//        List<BookDto>books = findBookByAuthor(author);
//
//        if(books.size() < startItem){
//            list = Collections.emptyList();
//        } else {
//            int toIndex = Math.min(startItem + pageSize, books.size());
//            list = books.subList(startItem, toIndex);
//        }
//        Page<BookDto>bookPage = new PageImpl<BookDto>(list, PageRequest.of(currentPage, pageSize), books.size());
//
//        return bookPage;
//    }
//
//    public Page<BookDto> findPaginatedbyTitle(Pageable pageable, String title) {
//        int pageSize = pageable.getPageSize();
//        int currentPage=pageable.getPageNumber();
//        int startItem = currentPage * pageSize;
//        List<BookDto>list;
//        List<BookDto>books = findBookByTitle(title);
//
//        if(books.size() < startItem){
//            list = Collections.emptyList();
//        } else {
//            int toIndex = Math.min(startItem + pageSize, books.size());
//            list = books.subList(startItem, toIndex);
//        }
//        Page<BookDto>bookPage = new PageImpl<BookDto>(list, PageRequest.of(currentPage, pageSize), books.size());
//
//        return bookPage;
//    }

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
