package pl.sda.librarymanagementapp.rent;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {
    @NotNull
    Long id;
    @NotNull
    private LocalDate borrowDate;
    @NotNull
    private LocalDate returnDate;
}
