package pl.sda.librarymanagementapp.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.librarymanagementapp.domain.Book;
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



    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Book>books;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Adress userAdress;

}
