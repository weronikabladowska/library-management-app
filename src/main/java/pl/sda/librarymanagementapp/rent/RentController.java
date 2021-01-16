package pl.sda.librarymanagementapp.rent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.sda.librarymanagementapp.exceptions.BadRequestException;
import pl.sda.librarymanagementapp.user.LibraryUser;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
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
    public RentDto createRent(@RequestBody @Valid RentDto rentDto) {
        if (rentDto == null) {
            throw new BadRequestException("Cannot create Rent from null RentDto");
        }
        Long userId = rentDto.getLibraryUser().getId();
        Long bookId = rentDto.getBookId();
        Rent rent = rentService.createRent(bookId, userId);
        return rentMapper.rentToRentDto(rentService.createRent(rent.getBookId(), rent.getLibraryUser().getId()));

    }

    @PatchMapping("rents/return")
    public RentDto returnBook(@RequestBody RentDto rentDto) {
        rentService.returnBook(rentDto);
        return rentDto;
    }

}
