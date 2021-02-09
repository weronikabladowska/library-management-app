package pl.sda.librarymanagementapp.adress;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.sda.librarymanagementapp.user.LibraryUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    @Column (name="city")
    private String city;
    @Column (name="street")
    private String street;
    @Column (name="houseNumber")
    private String houseNumber;
    @Column (name="postCode")
    private String postCode;

    @JsonIgnore
    @OneToMany (mappedBy = "userAddress")
    private List<LibraryUser> libraryUsers;


}
