package pl.sda.librarymanagementapp.model.mapper;

import org.mapstruct.Mapper;
import pl.sda.librarymanagementapp.domain.user.Address;
import pl.sda.librarymanagementapp.model.adress.AddressDto;

@Mapper
public interface AdressMapper {

    Address adressDTOToAdrres(AddressDto addressDTO);

    AddressDto adressToAdrresDTO(Address address);
}
