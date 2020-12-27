package pl.sda.librarymanagementapp.model.mapper;

import org.mapstruct.Mapper;
import pl.sda.librarymanagementapp.domain.user.Address;
import pl.sda.librarymanagementapp.model.adress.AddressDTO;

@Mapper
public interface AdressMapper {

    Address adressDTOToAdrres(AddressDTO addressDTO);

    AddressDTO adressToAdrresDTO(Address address);
}
