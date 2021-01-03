package pl.sda.librarymanagementapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.sda.librarymanagementapp.domain.user.Library_user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @ManyToMany(mappedBy = "rents")
    @EqualsAndHashCode.Exclude
    List<Library_user> readersList = new ArrayList<>();

//    @OneToOne
//    Reservation reservation;

}
