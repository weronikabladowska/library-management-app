package pl.sda.librarymanagementapp.rent;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.librarymanagementapp.user.LibraryUser;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {
    @NotNull
    Long id;
    @NotNull
    private Long bookId;
    @NotNull
    private boolean active;

}
