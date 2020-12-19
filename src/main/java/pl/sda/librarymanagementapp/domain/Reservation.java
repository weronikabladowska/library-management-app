package pl.sda.librarymanagementapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue
    Long id;
    private LocalDate reservationDate;

    @OneToOne
    Book book;

    @ManyToOne
    private LibraryUser libraryUser;


}
