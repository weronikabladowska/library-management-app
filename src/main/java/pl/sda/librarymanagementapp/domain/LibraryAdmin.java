package pl.sda.librarymanagementapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "libraryAdmins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryAdmin {

    @Id
    @GeneratedValue
    Long id;

    private String userName;
    private String password;
}
