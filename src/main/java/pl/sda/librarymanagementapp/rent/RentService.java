package pl.sda.librarymanagementapp.rent;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.book.BookService;
import pl.sda.librarymanagementapp.exceptions.BadRequestException;
import pl.sda.librarymanagementapp.exceptions.NotFoundException;
import pl.sda.librarymanagementapp.user.LibraryUser;
import pl.sda.librarymanagementapp.user.UserRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RentService {

    private final RentMapper rentMapper;
    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    public RentDto findRentById(@NotNull Long id) {
        if (rentRepository.findRentById(id) == null || id == null) {
            throw new NotFoundException("Cannot find rent with ID: " + id);
        }
        final Rent rent = rentRepository.findRentById(id);
        return rentMapper.rentToRentDto(rent);

    }

    public List<RentDto> findRentByBookId(@NotNull Long bookId) {
        if (rentRepository.findRentByBookId(bookId).isEmpty() || bookId == null) {
            throw new NotFoundException("Book with ID: " + bookId + " cannot be found");
        }
        return rentRepository
                .findRentByBookId(bookId)
                .stream()
                .map(rentMapper::rentToRentDto)
                .collect(Collectors.toList());
    }

    public List<RentDto> findRentByLibraryUserId(@NotNull Long userId) {
        if (rentRepository.findRentByLibraryUserId(userId).isEmpty() || userId == null) {
            throw new NotFoundException("Cannot find user with ID: " + userId);
        }
        return rentRepository
                .findRentByLibraryUserId(userId)
                .stream()
                .map(rentMapper::rentToRentDto)
                .collect(Collectors.toList());
    }

    public List<RentDto> findActiveRents(boolean active) {
        return rentRepository
                .findRentByActive(active)
                .stream()
                .map(rentMapper::rentToRentDto)
                .collect(Collectors.toList());
    }

    public List<RentDto> findDelayedRents() {
        return rentRepository.findRentByActiveAndReturnDateBefore(true, LocalDate.now())
                .stream()
                .map(rentMapper::rentToRentDto)
                .collect(Collectors.toList());
    }

    public Rent createRent(Long bookId, Long userId) {

        if (bookId == null || userId == null || userRepository.findById(userId).isEmpty() || bookService.findBookById(bookId).isEmpty()) {
            throw new NotFoundException("Incorrect Book or User ID");
        }

        if (rentRepository.findRentByActive(true).stream().filter(rent -> rent.getBookId().equals(bookId)).count() > 0) {
            throw new BadRequestException("Book with ID: " + bookId + " is already borrowed");
        }
        LibraryUser user = userRepository.findById(userId).get();
        Rent.RentBuilder builder = Rent.builder()
                .bookId(bookId)
                .libraryUser(user)
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now().plusMonths(1))
                .active(true);

        Rent rent = builder.build();
        return (rentRepository.save(rent));

    }

    public void returnBook(RentDto rentDto) {

        Rent rent = rentRepository.findRentById(rentDto.getId());
        rent.setActive(false);
    }

    public ResponseEntity<RentDto> toResponseEntity(Rent rent) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(rentMapper.rentToRentDto(rent));
    }


}
