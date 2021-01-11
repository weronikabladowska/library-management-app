package pl.sda.librarymanagementapp.rent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;
import pl.sda.librarymanagementapp.user.LibraryUser;

import javax.persistence.*;
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
    private boolean active;
    private Long bookId;

    @JsonIgnore
    @ManyToOne
    private LibraryUser libraryUser;

}
