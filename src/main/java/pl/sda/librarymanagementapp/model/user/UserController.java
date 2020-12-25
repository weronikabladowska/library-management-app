package pl.sda.librarymanagementapp.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.librarymanagementapp.domain.user.Adress;
import pl.sda.librarymanagementapp.domain.user.Library_user;
import pl.sda.librarymanagementapp.exceptions.BadBoundaryException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody Library_user user) {
        return userService.createUser(user);
    }

//   ToDO:// User musi posiadać adress, ale jak testować metedę z dwoma obiektami do dodania (?)
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserDTO createUser(@RequestBody Library_user user, @RequestBody Adress adress) {
//        return userService.createUser(user, adress);
//    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable final Long id) {
        return userService.findUserById(id);
    }

    @GetMapping
    public Page<UserDTO> getPageOfUsers(@RequestParam(name = "pageNum") final Integer pageNum, @RequestParam(name = "pageSize") final Integer pageSize) {
        if (pageNum<=0 && pageSize<0) {
        return userService.getPageOfUsers(pageNum, pageSize);}
        else throw new BadBoundaryException("Numer strony i wielkość strony muszą być wartościami dodatnimi");
    }


}
