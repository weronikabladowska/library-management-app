package pl.sda.librarymanagementapp.model.book;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.librarymanagementapp.domain.book.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findBookByTitle(String title);
}
