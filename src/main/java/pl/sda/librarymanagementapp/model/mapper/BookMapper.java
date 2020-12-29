package pl.sda.librarymanagementapp.model.mapper;

import org.mapstruct.Mapper;
import pl.sda.librarymanagementapp.domain.Book;
import pl.sda.librarymanagementapp.model.book.BookDto;

@Mapper
public interface BookMapper {

    Book toBook(BookDto bookDto);
    BookDto toBookDto(Book book);


}
