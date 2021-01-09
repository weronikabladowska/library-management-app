package pl.sda.librarymanagementapp.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.sda.librarymanagementapp.user.LibraryUser;
import pl.sda.librarymanagementapp.user.UserDto;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    LibraryUser userDTOtoUser(UserDto userDto);
    UserDto userToUserDto(LibraryUser libraryUser);
}
