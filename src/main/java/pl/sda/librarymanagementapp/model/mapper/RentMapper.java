package pl.sda.librarymanagementapp.model.mapper;

import org.mapstruct.Mapper;
import pl.sda.librarymanagementapp.domain.rent.Rent;
import pl.sda.librarymanagementapp.model.rent.RentDTO;

@Mapper
public interface RentMapper {
    Rent rentDTOToRent (RentDTO rentDTO);
    RentDTO rentToRentDTO(Rent rent);
}
