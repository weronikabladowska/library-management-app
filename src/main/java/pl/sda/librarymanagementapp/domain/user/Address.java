package pl.sda.librarymanagementapp.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String city;
    private String street;
    private String houseNumber;
    private String postCode;

    @JsonIgnore
    @OneToMany (mappedBy = "userAddress")
//    @JoinColumn (name = "user_id")
    private List<Library_user> libraryusers;


}
