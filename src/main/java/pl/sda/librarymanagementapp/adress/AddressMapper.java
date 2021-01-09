package pl.sda.librarymanagementapp.adress;

import org.mapstruct.Mapper;
import pl.sda.librarymanagementapp.adress.Address;
import pl.sda.librarymanagementapp.adress.AddressDto;

@Mapper
public interface AddressMapper {

    Address addressDtoToAddress(AddressDto addressDTO);

    AddressDto addressToAddressDto(Address address);
}
