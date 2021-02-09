package pl.sda.librarymanagementapp.rent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    Rent findRentById(Long id);

    List<Rent> findRentByLibraryUserId(Long userId);

    List<Rent> findRentByBookId(Long id);

    List<Rent> findRentByActive(boolean active);
    List<Rent> findRentByActiveAndBookId(boolean active, Long bookId);

    //to find delayed returns
    List<Rent>findRentByActiveAndReturnDateBefore(boolean active, LocalDate date);
}
