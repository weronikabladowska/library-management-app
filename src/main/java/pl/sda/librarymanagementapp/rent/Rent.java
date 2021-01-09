package pl.sda.librarymanagementapp.rent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;
import pl.sda.librarymanagementapp.book.Book;
import pl.sda.librarymanagementapp.user.LibraryUser;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Component
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
    private boolean isBorrowed;

    @JsonIgnore
    @ManyToOne
    private Book borrowedBook;

    @JsonIgnore
    @ManyToOne
    private LibraryUser libraryUser;

}
