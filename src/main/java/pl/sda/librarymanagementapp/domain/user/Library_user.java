package pl.sda.librarymanagementapp.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pl.sda.librarymanagementapp.domain.Rent;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Library_user {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column (name = "yearOfBorn")
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
    @ManyToMany (mappedBy = "readersList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Rent> rents;

    @JsonIgnore
    @OneToMany (mappedBy = "libraryusers")
//    @JoinColumn(name = "user_id")
    private Address userAddress;

}
