package pl.sda.librarymanagementapp.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity (name ="adress")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Column (name ="city")
    private String city;
    @Column (name ="street")
    private String street;
    @Column (name="houseNumber")
    private String houseNumber;
    @Column (name ="postCode")
    private String postCode;

    @JsonIgnore
    @ManyToOne
//    @JoinColumn (name = "user_id")
    private List<Library_user> libraryusers;


}
