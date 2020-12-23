package pl.sda.librarymanagementapp.Book;

import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    Book toBook(BookDto bookDto);
    BookDto toBookDto(Book book);


}
