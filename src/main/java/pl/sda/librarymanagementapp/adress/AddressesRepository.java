package pl.sda.librarymanagementapp.adress;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressesRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressByStreet(String street);

}
