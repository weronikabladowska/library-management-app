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
    private final RentMapper rentMapper;

    @GetMapping(value = "/rents", params = "id")
    public List<RentDto> findRentsByBookId(@RequestParam(name = "id") Long id) {
        return rentService.findRentByBookId(id);
    }

    @GetMapping(value = "/rents", params = "libraryUser")
    public List<RentDto> findRentsByLibraryUser(@RequestParam(name = "libraryUser") LibraryUser libraryUser) {
        return rentService.findRentByLibraryUser(libraryUser);
    }

    @GetMapping(value = "/rents/active")
    public List<RentDto> findActiveRents(){
        return rentService.findActiveRents(true);
    }

    @GetMapping("/rents/delayed")
    public List<RentDto> findDelayedRents(){
        return rentService.findDelayedRents();
    }

    @GetMapping(value = "/rents", params = "rentId")
    public RentDto findRentById(@RequestParam(name = "rentId") Long rentId){
        return rentService.findRentById(rentId);
    }

    @PostMapping("/rents/create")
    ResponseEntity<RentDto>createRent(@RequestBody RentDto rentDto){
        LibraryUser libraryUser = rentDto.getLibraryUser();
        Long bookId = rentDto.getBookId();

        Rent rent = rentService.createRent(bookId, libraryUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(rentMapper.rentToRentDto(rent));
    }

}
