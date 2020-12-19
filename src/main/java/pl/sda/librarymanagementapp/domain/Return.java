package pl.sda.librarymanagementapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "returns")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Return {

    @Id
    @GeneratedValue
    Long id;

    private LocalDate returnDate;

    @OneToOne
    Reservation reservation;

}
