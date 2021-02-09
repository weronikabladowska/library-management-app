package pl.sda.librarymanagementapp.rent;

import org.mapstruct.Mapper;

@Mapper
public interface RentMapper {
    RentDto rentToRentDto(Rent rent);
}
