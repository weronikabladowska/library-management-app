package pl.sda.librarymanagementapp.book;

import org.mapstruct.Mapper;
import pl.sda.librarymanagementapp.book.SingleBook;

@Mapper
public interface BookMapper {

    BookDto toBookDto(SingleBook singleBook);


}
