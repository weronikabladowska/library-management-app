package pl.sda.librarymanagementapp.domain.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.sda.librarymanagementapp.domain.LibraryUser;

import javax.persistence.*;

@Entity(name = "books")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private String placeOfPublication;
    private String isbn;

    @ManyToOne
    private LibraryUser libraryUser;


}
