package pl.sda.librarymanagementapp.Book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BookSourceResponse {

    private List<SingleBook> bibs;

    @Data
    public static class SingleBook{

        private String title;
        private String author;
        private String publisher;
        private String placeOfPublication;
        private String publicationYear;
        private String isbn;
    }


}
