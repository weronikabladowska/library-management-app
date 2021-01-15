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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public Page<UserDto> getPageOfUsers(@RequestParam(name = "pageNum") final Integer pageNum, @RequestParam(name = "pageSize") final Integer pageSize) {
        if (pageNum >= 0 && pageSize > 0) {
            return userService.getPageOfUsers(pageNum, pageSize);
        } else throw new BadBoundaryException("Numer strony i wielkość strony muszą być wartościami dodatnimi");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/lastName/{lastName}")
    public List<UserDto> getUserByLastName(@PathVariable final String lastName) {
        return userService.findUserByLastName(lastName);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable final String email) {
        return userService.findUserByEmail(email);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/tel/{tel}")
    public UserDto getUserByTelNumber(@PathVariable final Long tel) {
        return userService.findUserByTelNumber(tel);
    }


}
