package pl.sda.librarymanagementapp.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private String placeOfPublication;
    private String isbn;
}
