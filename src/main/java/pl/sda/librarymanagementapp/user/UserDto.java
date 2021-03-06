package pl.sda.librarymanagementapp.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull
    Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Integer year;
    @NotNull
    private String email; //email is login
    private Long tel;
    @NotNull
    private Role role;
}
