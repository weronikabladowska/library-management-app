package pl.sda.librarymanagementapp.rent;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.book.BookService;
import pl.sda.librarymanagementapp.exceptions.NotFoundException;
import pl.sda.librarymanagementapp.user.LibraryUser;
import pl.sda.librarymanagementapp.user.UserRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RentService {

    private final RentMapper rentMapper;
    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    public RentDto findRentById(Long id) {
        if (rentRepository.findRentById(id).equals(null)) {
            throw new NotFoundException("Cannot find rent with ID: " + id);
        } else {
            final Rent rent = rentRepository.findRentById(id);
            return rentMapper.rentToRentDto(rent);
        }
    }

    public List<RentDto> findRentByBookId(Long bookId) {
        if (rentRepository.findRentByBookId(bookId).isEmpty()) {
            throw new NotFoundException("Book with ID: " + bookId + " cannot be found");
        } else
            return rentRepository
                    .findRentByBookId(bookId)
                    .stream()
                    .map(rentMapper::rentToRentDto)
                    .collect(Collectors.toList());
    }

    public List<RentDto> findRentByLibraryUserId(Long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("Cannot find user with ID: " + userId);
        } else
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

    public Rent createRent(@NotNull Long bookId, @NotNull Long userId) {

        if (!userRepository.findById(userId).isPresent() || bookService.findBookById(bookId).isEmpty()) {
            throw new NotFoundException("Incorrect Book or User ID");
        } else {
            LibraryUser user = userRepository.findById(userId).get();
            Rent.RentBuilder builder = Rent.builder()
                    .bookId(bookId)
                    .libraryUser(user)
                    .borrowDate(LocalDate.now())
                    .returnDate(LocalDate.now().plusMonths(1))
                    .active(true);

            Rent rent = builder.build();
            return rentRepository.save(rent);
        }
    }

    public void returnBook(RentDto rentDto) {
        rentRepository.findRentById(rentDto.getId()).setActive(false);
    }


}
