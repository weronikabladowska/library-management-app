package pl.sda.librarymanagementapp.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.librarymanagementapp.domain.user.libraryUser;

import java.util.List;

public interface UserRepository extends JpaRepository<libraryUser, Long> {

    List<libraryUser> findLibrary_userByLastName(String lastName);

    libraryUser findLibrary_userByEmail(String email);

    libraryUser findLibrary_userByTel(Long telNumber);

}
