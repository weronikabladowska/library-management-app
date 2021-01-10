package pl.sda.librarymanagementapp.adress;

import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {

    Address addressDtoToAddress(AddressDto addressDTO);

    AddressDto addressToAddressDto(Address address);
}
