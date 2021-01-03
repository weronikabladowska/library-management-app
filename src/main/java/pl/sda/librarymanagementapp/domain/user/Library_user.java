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

    private String firstName;
    private String lastName;
    private Integer year;
    private String password;
    private String email;
    private Long tel;
    private Role role;

    @JsonIgnore
    @ManyToMany(mappedBy = "readersList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    List<Rent> rents;

    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "user_id")
    private Address userAddress;

}
