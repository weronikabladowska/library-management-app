package pl.sda.librarymanagementapp.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.sda.librarymanagementapp.domain.user.Library_user;
import pl.sda.librarymanagementapp.model.user.UserDTO;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    Library_user userDTOtoUser (UserDTO userDTO);
    UserDTO userToUserDTO (Library_user library_user);
}
