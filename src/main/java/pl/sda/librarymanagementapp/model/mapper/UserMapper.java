package pl.sda.librarymanagementapp.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.sda.librarymanagementapp.domain.user.libraryUser;
import pl.sda.librarymanagementapp.model.user.UserDto;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    libraryUser userDTOtoUser(UserDto userDto);
    UserDto userToUserDTO(libraryUser libraryUser);
}
