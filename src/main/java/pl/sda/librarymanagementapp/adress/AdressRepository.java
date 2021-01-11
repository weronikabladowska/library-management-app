package pl.sda.librarymanagementapp.adress;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdressRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressByStreet(String street);

}
