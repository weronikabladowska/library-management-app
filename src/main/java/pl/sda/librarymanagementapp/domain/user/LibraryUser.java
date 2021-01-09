package pl.sda.librarymanagementapp.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.sda.librarymanagementapp.domain.rent.Rent;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LibraryUser {

    @Id
    @GeneratedValue
    private Long id;
    @Column (name="firstName")
    private String firstName;
    @Column (name="lastName")
    private String lastName;
    @Column (name="year")
    private Integer year;
    @Column (name="password")
    private String password;
    @Column (name="email")
    private String email;
    @Column (name="tel")
    private Long tel;
    @Column (name="role")
    private Role role;

    @JsonIgnore
    @ManyToMany(mappedBy = "readersList")
    @EqualsAndHashCode.Exclude
    List<Rent> rents;

    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "user_id")
    private Address userAddress;




}
