package pl.sda.librarymanagementapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity (name ="adress")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adress {

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

    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;


}
