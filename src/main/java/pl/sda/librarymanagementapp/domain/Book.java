package pl.sda.librarymanagementapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String publicationYear;
    private String placeofPublication;

    @ManyToOne
    private LibraryUser libraryUser;
}
