package pl.sda.librarymanagementapp.domain.user;

import lombok.*;
import pl.sda.librarymanagementapp.domain.Book;
import pl.sda.librarymanagementapp.domain.Rent;
import pl.sda.librarymanagementapp.domain.user.Adress;
import pl.sda.librarymanagementapp.domain.user.Role;

import javax.persistence.*;
import java.util.List;

@Entity(name = "libraryUsers")
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



    @ManyToMany (mappedBy = "readersList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Rent> rents;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Adress userAdress;

}
