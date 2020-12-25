package pl.sda.librarymanagementapp.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.domain.user.Adress;
import pl.sda.librarymanagementapp.domain.user.Library_user;
import pl.sda.librarymanagementapp.model.mapper.UserMapper;

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

    //    Żeby stworzyć usera trzeba znać id Adresu lub wprowadzić nowy!
    public UserDTO createUser(Library_user user, Adress adress) {
        user.setUserAdress(adress);
        return userMapper.userToUserDTO(user);
    }

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
