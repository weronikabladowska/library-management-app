package pl.sda.librarymanagementapp.rent;

import org.mapstruct.Mapper;
import pl.sda.librarymanagementapp.rent.Rent;
import pl.sda.librarymanagementapp.rent.RentDto;

@Mapper
public interface RentMapper {
    RentDto rentToRentDto(Rent rent);
}
