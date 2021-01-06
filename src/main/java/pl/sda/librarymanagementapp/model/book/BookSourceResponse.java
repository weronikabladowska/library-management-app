package pl.sda.librarymanagementapp.model.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BookSourceResponse {

    private String nextPage;
    private List<SingleBook> bibs;
}
