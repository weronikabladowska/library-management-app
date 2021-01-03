package pl.sda.librarymanagementapp.domain.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.sda.librarymanagementapp.domain.rent.Rent;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @ManyToMany (mappedBy ="booksList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    List<Rent> rentsList;


}
