package pl.sda.librarymanagementapp.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.librarymanagementapp.rent.Rent;
import pl.sda.librarymanagementapp.user.LibraryUser;


import javax.persistence.*;
import java.util.List;
@Component
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

    @OneToMany(mappedBy = "borrowedBook")
    List<Rent> rents;


}
