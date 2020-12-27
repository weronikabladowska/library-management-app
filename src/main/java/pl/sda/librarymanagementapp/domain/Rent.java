package pl.sda.librarymanagementapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.sda.librarymanagementapp.domain.user.Library_user;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "rents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rent {

    @Id
    @GeneratedValue
    Long id;

    private LocalDate borrowDate;
    private LocalDate returnDate;

//    @ManyToMany (mappedBy = "rents")
//    @EqualsAndHashCode.Exclude
//    List<Library_user> readersList;

//    @OneToOne
//    Reservation reservation;

}
