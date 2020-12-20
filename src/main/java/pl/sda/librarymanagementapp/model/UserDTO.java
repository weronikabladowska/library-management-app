package pl.sda.librarymanagementapp.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Integer year;
    private String email;
    private Long tel;


}
