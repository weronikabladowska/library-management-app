package pl.sda.librarymanagementapp.book;

import org.mapstruct.Mapper;
import pl.sda.librarymanagementapp.book.Book;
import pl.sda.librarymanagementapp.book.BookDto;
import pl.sda.librarymanagementapp.book.SingleBook;

@Mapper
public interface BookMapper {

    Book toBook(BookDto bookDto);
    BookDto toBookDto(Book book);
    BookDto toBookDto(SingleBook singleBook);


}
