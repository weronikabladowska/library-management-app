package pl.sda.librarymanagementapp.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {


    LibraryUser userDtoToLibraryUser(UserDto userDto);
    LibraryUser userModelToUser (LibraryUserModel userModel);
    UserDto userToUserDto(LibraryUser libraryUser);
    LibraryUserModel userToUserModel(LibraryUser libraryUser);
}
