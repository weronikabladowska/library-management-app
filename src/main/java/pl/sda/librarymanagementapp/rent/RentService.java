package pl.sda.librarymanagementapp.rent;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.book.Book;
import pl.sda.librarymanagementapp.exceptions.BadRequestException;
import pl.sda.librarymanagementapp.user.LibraryUser;
import pl.sda.librarymanagementapp.user.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RentService {

    private final RentMapper rentMapper;
    private final RentRepository rentRepository;

    public RentDto findRentById(Long id) {
        final Rent rent = rentRepository.findRentById(id);
        return rentMapper.rentToRentDto(rent);
    }

    public List<RentDto> findRentByBook(Book book) {
        return rentRepository
                .findRentByBook(book)
                .stream()
                .map(rentMapper::rentToRentDto)
                .collect(Collectors.toList());
    }

    public List<RentDto> findRentByLibraryUser(LibraryUser libraryUser) {
        return rentRepository
                .findRentByLibraryUser(libraryUser)
                .stream()
                .map(rentMapper::rentToRentDto)
                .collect(Collectors.toList());
    }

    public List<RentDto> findActiveRents(boolean isBorrowed) {
        return rentRepository
                .findRentByBorrowedIs(isBorrowed)
                .stream()
                .map(rentMapper::rentToRentDto)
                .collect(Collectors.toList());
    }

    public List<RentDto> findDelayedRents() {
        return rentRepository.findRentByBorrowedIsAndReturnDateBefore(true, LocalDate.now())
                .stream()
                .map(rentMapper::rentToRentDto)
                .collect(Collectors.toList());
    }

    public Rent createRent(@NotNull Book book, @NotNull LibraryUser libraryUser) {
        Rent.RentBuilder builder = Rent.builder()
                .borrowedBook(book)
                .libraryUser(libraryUser)
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now().plusMonths(1))
                .isBorrowed(true);

        Rent rent = builder.build();
        return rentRepository.save(rent);
    }

    public void returnBook(Rent rent){
        rentRepository.findRentById(rent.getId()).setBorrowed(false);
    }


}
