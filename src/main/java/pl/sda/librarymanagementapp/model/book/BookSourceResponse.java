package pl.sda.librarymanagementapp.model.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BookSourceResponse {

    private List<SingleBook> bibs;
}
