package pl.sda.librarymanagementapp.book;

import lombok.Data;

import java.util.List;

@Data
public class BookSourceResponse {

    private String nextPage;
    private List<SingleBook> bibs;
}
