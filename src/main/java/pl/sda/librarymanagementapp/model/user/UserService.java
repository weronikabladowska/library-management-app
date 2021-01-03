package pl.sda.librarymanagementapp.model.user;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.domain.user.Library_user;
import pl.sda.librarymanagementapp.exceptions.BadRequestException;
import pl.sda.librarymanagementapp.model.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserDTO findUserById(Long id) {
        final Library_user user = userRepository.findById(id).orElseThrow();
        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> findUserByLastName(String lastName) {
        return userRepository.findLibrary_userByLastName(lastName)
                .stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserByEmail(String email) {
        return userMapper.userToUserDTO(userRepository.findLibrary_userByEmail(email));
    }

    public UserDTO findUserByTelNumber(Long number) {
        return userMapper.userToUserDTO(userRepository.findLibrary_userByTel(number));
    }

    public UserDTO createUser(@NotNull Library_user user) {

        if (user.getFirstName().trim().isEmpty()) {
            throw new BadRequestException("Pole z nazwą nie może być puste");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new BadRequestException("Pole z email nie może być puste");
        }
        if (user.getLastName().trim().isEmpty()) {
            throw new BadRequestException("Pole z nazwiskiem nie może być puste");
        }
        if (user.getRole() == null) {
            throw new BadRequestException("Pole z nazwą roli nie może być puste");
        }

        return userMapper.userToUserDTO(user);
    }

//    //    Żeby stworzyć usera trzeba znać id Adresu lub wprowadzić nowy!
//    public UserDTO createUser(Library_user user, Adress adress) {
//        user.setUserAdress(adress);
//        return userMapper.userToUserDTO(user);
//    }

    public Page<UserDTO> getPageOfUsers(Integer pageNum, Integer pageSize) {
        final Page<Library_user> page = userRepository.findAll(PageRequest.of(pageNum, pageSize));
        return page.map(userMapper::userToUserDTO);
    }

    public Page<UserDTO> getPageOfUsers(Integer pageNum, Integer pageSize, String sortBy) {
        final Page<Library_user> page = userRepository.findAll(PageRequest.of(pageNum, pageSize));
        Sort.by(sortBy).descending();
        return page.map(userMapper::userToUserDTO);
    }


}
