package pl.sda.librarymanagementapp.domain.rent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.sda.librarymanagementapp.domain.book.Book;
import pl.sda.librarymanagementapp.domain.user.Library_user;

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
    List<Library_user> readersList;

    @JsonIgnore
    @ManyToMany
    @EqualsAndHashCode.Exclude
    List<Book> booksList;



//    @OneToOne
//    Reservation reservation;

}
