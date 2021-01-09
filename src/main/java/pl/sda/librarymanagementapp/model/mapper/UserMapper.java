package pl.sda.librarymanagementapp.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.sda.librarymanagementapp.domain.user.Library_user;
import pl.sda.librarymanagementapp.model.user.UserDto;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    Library_user userDTOtoUser(UserDto userDTO);
    UserDto userToUserDTO(Library_user library_user);
}
