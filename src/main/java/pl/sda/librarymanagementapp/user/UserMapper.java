package pl.sda.librarymanagementapp.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    LibraryUser userDtoToLibraryUser(UserDto userDto);
    UserDto userToUserDto(LibraryUser libraryUser);
}
