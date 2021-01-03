package pl.sda.librarymanagementapp.model.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.librarymanagementapp.domain.Rent;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
