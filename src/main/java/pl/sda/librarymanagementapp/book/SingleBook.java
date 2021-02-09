package pl.sda.librarymanagementapp.book;

import lombok.Data;

@Data
public class SingleBook {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String placeOfPublication;
    private String publicationYear;
    private String isbnIssn;
}
