package pl.sda.librarymanagementapp.user;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    LibraryUser userModelToUser(LibraryUserModel userModel);

    UserDto userToUserDto(LibraryUser libraryUser);
}
