package pl.sda.librarymanagementapp.model.mapper;

import org.mapstruct.Mapper;
import pl.sda.librarymanagementapp.domain.book.Book;
import pl.sda.librarymanagementapp.model.book.BookDto;
import pl.sda.librarymanagementapp.model.book.SingleBook;

@Mapper
public interface BookMapper {

    Book toBook(BookDto bookDto);
    BookDto toBookDto(Book book);
    BookDto toBookDto(SingleBook singleBook);


}
