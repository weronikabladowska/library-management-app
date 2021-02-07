package pl.sda.librarymanagementapp.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.librarymanagementapp.exceptions.BadBoundaryException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody LibraryUserModel user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping
    public String deleteUser(@RequestBody Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/page")
    public Page<UserDto> getPageOfUsers(@RequestParam(name = "pageNum") final Integer pageNum, @RequestParam(name = "pageSize") final Integer pageSize) {
        if (pageNum >= 0 && pageSize > 0) {
            return userService.getPageOfUsers(pageNum, pageSize);
        } else throw new BadBoundaryException("Numer strony i wielkość strony muszą być wartościami dodatnimi");
    }

    @GetMapping
    public List<UserDto> getListUsers() {
        return userService.getListOfUsers();
    }

    @GetMapping("/lastName/{lastName}")
    public List<UserDto> getUserByLastName(@PathVariable final String lastName) {
        return userService.findUserByLastName(lastName);
    }

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable final String email) {
        return userService.findUserByEmail(email);
    }

//    @GetMapping("/tel/{tel}")
//    public UserDto getUserByTelNumberSingel(@PathVariable final Long tel) {
//        return userService.findUserByTelNumber(tel);
//    }

    @GetMapping("/tel/{tel}")
    public List<UserDto> getUserByTelNumber(@PathVariable final Long tel) {
        return userService.findUserByTelNumber(tel);
    }

    @GetMapping("/authentication/{email}/{password}")
    public UserDto doesAuthenticatedUserExist(@PathVariable final String email, @PathVariable final String password) {
        return userService.findIfUserAlreadyExists(email, password);
    }
}
