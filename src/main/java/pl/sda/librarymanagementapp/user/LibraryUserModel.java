package pl.sda.librarymanagementapp.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryUserModel {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private Integer year;
    @NotNull
    private String email; //email jest loginem
    private Long tel;
    @NotNull
    private String password;
    @NotNull
    private Role role;
}
