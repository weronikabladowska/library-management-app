package pl.sda.librarymanagementapp.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.sda.librarymanagementapp.exceptions.BadRequestException;
import pl.sda.librarymanagementapp.user.LibraryUserAdapter;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:4200")

public class RentController {

    private final RentService rentService;
    private final RentMapper rentMapper;


    @GetMapping(value = "/rents", params = "bookId")
    public List<RentDto> findRentsByBookId(@RequestParam(name = "bookId") Long id) {
        return rentService.findRentByBookId(id);
    }

    @GetMapping(value = "/rents", params = "libraryUser")
    public List<RentDto> findRentsByLibraryUser(@RequestParam(name = "libraryUser") Long userId) {
        return rentService.findRentByLibraryUserId(userId);
    }

    @GetMapping(value = "/rents/active")
    public List<RentDto> findActiveRents() {
        return rentService.findActiveRents(true);
    }

    @GetMapping("/rents/delayed")
    public List<RentDto> findDelayedRents() {
        return rentService.findDelayedRents();
    }

    @GetMapping(value = "/rents", params = "rentId")
    public RentDto findRentById(@RequestParam(name = "rentId") Long rentId) {
        return rentService.findRentById(rentId);
    }

    @PostMapping("/rents/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RentDto createRent(@RequestBody @Valid RentDto rentDto, @AuthenticationPrincipal LibraryUserAdapter libraryUserAdapter) {
        if (rentDto == null) {
            throw new BadRequestException("Cannot create Rent from null RentDto");
        }
        Long userId = libraryUserAdapter.getId();
        Long bookId = rentDto.getBookId();
        return rentService.createRent(bookId, userId);
    }

    @PutMapping("/rents/return")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnBook(@RequestParam (name = "rentId") Long rentId) {
        rentService.returnBook(rentId);
    }

}
