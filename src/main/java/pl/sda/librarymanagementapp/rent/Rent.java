package pl.sda.librarymanagementapp.rent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.librarymanagementapp.user.LibraryUser;

import javax.persistence.*;
import java.time.LocalDate;

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
    @ManyToOne(cascade = CascadeType.ALL)
    private LibraryUser libraryUser;


}
