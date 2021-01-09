package pl.sda.librarymanagementapp.model.mapper;

import org.mapstruct.Mapper;
import pl.sda.librarymanagementapp.domain.rent.Rent;
import pl.sda.librarymanagementapp.model.rent.RentDto;

@Mapper
public interface RentMapper {
    Rent rentDTOToRent (RentDto rentDTO);
    RentDto rentToRentDTO(Rent rent);
}
