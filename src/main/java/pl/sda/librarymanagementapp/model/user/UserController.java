package pl.sda.librarymanagementapp.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.librarymanagementapp.domain.user.Library_user;
import pl.sda.librarymanagementapp.exceptions.BadBoundaryException;
import pl.sda.librarymanagementapp.model.mapper.UserMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody Library_user user) {
        return userService.createUser(user);
    }

//   ToDO:// User musi posiadać adress, ale jak testować metedę z dwoma obiektami do dodania (?)

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserDTO createUser(@RequestBody Library_user user, @RequestBody Adress adress) {
//        return userService.createUser(user, adress);
//    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

//ToDo: Jak przekazać pageNum i PageSize w testach?
    @GetMapping
    public Page<UserDto> getPageOfUsers(@RequestParam(name = "pageNum") final Integer pageNum, @RequestParam(name = "pageSize") final Integer pageSize) {
        if (pageNum >= 0 && pageSize > 0) {
            return userService.getPageOfUsers(pageNum, pageSize);
        } else throw new BadBoundaryException("Numer strony i wielkość strony muszą być wartościami dodatnimi");
    }

    @GetMapping("/lastName/{lastName}")
    public List<UserDto> getUserByLastName(@PathVariable final String lastName) {
        return userService.findUserByLastName(lastName);
    }

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable final String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/tel/{tel}")
    public UserDto getUserByTelNumber(@PathVariable final Long tel) {
        return userService.findUserByTelNumber(tel);
    }


}
