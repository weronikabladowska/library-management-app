package pl.sda.librarymanagementapp.user;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.exceptions.BadRequestException;
import pl.sda.librarymanagementapp.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto findUserById(Long id) {
        final LibraryUser user = userRepository.findById(id).orElseThrow();
        return userMapper.userToUserDto(user);
    }

    public List<UserDto> findUserByLastName(String lastName) {
        return userRepository.findLibrary_userByLastName(lastName)
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto findUserByEmail(String email) {
        return userMapper.userToUserDto(userRepository.findLibrary_userByEmail(email));
    }

    public UserDto findIfUserAlreadyExists(String email, String password) {

        if (userRepository.findLibrary_userByEmail(email) == null) {
            throw new NotFoundException("User " + " not found");
        } else {
            return userMapper.userToUserDto(userRepository.findLibrary_userByEmail(email));
        }
    }


    public List<UserDto> findUserByTelNumber(Long number) {
        return userRepository.findLibrary_userByTel(number)
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto createUser(@NotNull LibraryUserModel user) {

        if (user.getFirstName().trim().isEmpty()) {
            throw new BadRequestException("Pole z nazwą nie może być puste");
        }
        if (user.getPassword().trim().isEmpty()) {
            throw new BadRequestException("Pole z hasłem nie może być puste");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new BadRequestException("Pole z email nie może być puste");
        }
        if (user.getLastName().trim().isEmpty()) {
            throw new BadRequestException("Pole z nazwiskiem nie może być puste");
        }
        LibraryUser libraryUser = userMapper.userModelToUser(user);
        libraryUser.setRole(Role.USER);
        libraryUser.setPassword(passwordEncoder.encode(user.getPassword()));
        LibraryUser savedUser = userRepository.save(libraryUser);
        return userMapper.userToUserDto(savedUser);
    }

    public Page<UserDto> getPageOfUsers(Integer pageNum, Integer pageSize) {
        final Page<LibraryUser> page = userRepository.findAll(PageRequest.of(pageNum, pageSize));
        return page.map(userMapper::userToUserDto);
    }

    public List<UserDto> getListOfUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());

    }

}
