package pl.sda.librarymanagementapp.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.librarymanagementapp.user.LibraryUser;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;


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
    ResponseEntity<RentDto> createRent(@RequestBody RentDto rentDto) {
        Long userId = rentDto.getLibraryUserId();
        Long bookId = rentDto.getBookId();
        Rent rent = rentService.createRent(bookId, userId);
        return rentService.toResponseEntity(rent);
    }

    @PutMapping("rents/return")
    public void returnBook(@RequestBody RentDto rentDto) {
        rentService.returnBook(rentDto);
    }

}
