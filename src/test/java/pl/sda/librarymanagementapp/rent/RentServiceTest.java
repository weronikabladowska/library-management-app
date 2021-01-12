package pl.sda.librarymanagementapp.rent;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import pl.sda.librarymanagementapp.user.LibraryUser;
//import pl.sda.librarymanagementapp.user.Role;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//@AutoConfigureMockMvc
//class RentServiceTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void findRentById() {
//    }
//
//    @Test
//    void findRentByBookId() {
//    }
//
//    @Test
//    void findRentByLibraryUserId() {
//    }
//
//    @Test
//    void findActiveRents() {
//    }
//
//    @Test
//    void findDelayedRents() {
//    }
//
//    @Test
//    void createRent_returns201status() {
//    }
//
//    @Test
//    void returnBook() {
//    }
//
//
//    private Rent createRent() {
//        return new Rent.RentBuilder()
//                .bookId(1000562L)
//                .libraryUser(new LibraryUser())
//                .active(true)
//                .borrowDate(LocalDate.now())
//                .returnDate(LocalDate.now().plusMonths(1)).build();
//
//}
