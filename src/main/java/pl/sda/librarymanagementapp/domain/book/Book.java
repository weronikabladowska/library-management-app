package pl.sda.librarymanagementapp.domain.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.librarymanagementapp.domain.user.LibraryUser;


import javax.persistence.*;

@Entity(name = "books")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publicationYear")
    private String publicationYear;

    @Column(name = "placeofPublication")
    private String placeOfPublication;

    @Column(name = "isbn")
    private String isbn;

    @ManyToOne
    private LibraryUser libraryUser;


}
