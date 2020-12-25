package pl.sda.librarymanagementapp.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.sda.librarymanagementapp.domain.LibraryUser;
import pl.sda.librarymanagementapp.model.UserDTO;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    LibraryUser userToUserDTO (UserDTO userDTO);
    UserDTO userDTOtoUser (LibraryUser libraryUser);
}
