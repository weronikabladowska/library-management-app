package pl.sda.librarymanagementapp.model.adress;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.librarymanagementapp.domain.user.Address;

import java.util.List;

public interface AdressRepository extends JpaRepository<Address, Long> {

    List<Address> findAdressByStreet(String street);

}
