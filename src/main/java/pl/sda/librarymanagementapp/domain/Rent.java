package pl.sda.librarymanagementapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.sda.librarymanagementapp.domain.user.Library_user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    @JsonIgnore
    @ManyToMany(mappedBy = "rents")
    @EqualsAndHashCode.Exclude
    List<Library_user> readersList;

//    @OneToOne
//    Reservation reservation;

}
