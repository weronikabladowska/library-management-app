package pl.sda.librarymanagementapp.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.librarymanagementapp.book.Book;
import pl.sda.librarymanagementapp.user.LibraryUser;

import java.time.LocalDate;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    Rent findRentById(Long id);

    List<Rent> findRentByLibraryUser(LibraryUser libraryUser);

    List<Rent> findRentByBook(Book book);

    List<Rent>findRentByBorrowedIs(boolean isBorrowed);

    //to find delayed returns
    List<Rent>findRentByBorrowedIsAndReturnDateBefore(boolean isBorrowed, LocalDate date);
}
