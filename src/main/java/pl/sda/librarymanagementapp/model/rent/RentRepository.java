package pl.sda.librarymanagementapp.model.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.librarymanagementapp.domain.book.Book;
import pl.sda.librarymanagementapp.domain.rent.Rent;

import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {

}
