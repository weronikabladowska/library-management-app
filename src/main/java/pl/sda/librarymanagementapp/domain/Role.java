package pl.sda.librarymanagementapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "role_name")
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude  //zeby relacje wzajemne sie nie zapetlaly
    @ManyToMany
    @JoinTable(name = "roles_to_users", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users;

}
