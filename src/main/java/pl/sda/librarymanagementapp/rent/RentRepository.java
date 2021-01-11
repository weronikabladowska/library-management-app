package pl.sda.librarymanagementapp.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.sda.librarymanagementapp.user.LibraryUser;

import java.time.LocalDate;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    Rent findRentById(Long id);

    List<Rent> findRentByLibraryUser(LibraryUser libraryUser);

    List<Rent> findRentByBookId(Long id);

    List<Rent> findRentByActive(boolean active);

    //to find delayed returns
    List<Rent>findRentByActiveAndReturnDateBefore(boolean active, LocalDate date);
}
