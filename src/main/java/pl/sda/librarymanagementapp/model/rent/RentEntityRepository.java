//package pl.sda.librarymanagementapp.model.rent;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import pl.sda.librarymanagementapp.domain.rent.Rent;
//import pl.sda.librarymanagementapp.model.book.BookRepository;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@Transactional
//public class RentEntityRepository {
//
//    private final BookRepository bookRepository;
//
//    public RentEntityRepository(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//    Rent findByBookTitle(String title) {
//        List<Rent> rentsList = bookRepository.findBookByTitle(title).getRentsList(;
//        int size = rentsList.size();
//        return rentsList.get(size - 1);
//
//    }
//
//}
