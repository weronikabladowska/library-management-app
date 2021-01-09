package pl.sda.librarymanagementapp.rent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.sda.librarymanagementapp.book.Book;
import pl.sda.librarymanagementapp.user.LibraryUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rent {

    @Id
    @GeneratedValue
    Long id;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    @JsonIgnore
    @ManyToMany
    @EqualsAndHashCode.Exclude
    List<LibraryUser> readersList;

    @JsonIgnore
    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Book> booksList;

    String name;



//    @OneToOne
//    Reservation reservation;

}
