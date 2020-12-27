package pl.sda.librarymanagementapp.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

//    @OneToOne
//    @JoinColumn (name = "user_id")
//    private Library_user libraryuser;


}
