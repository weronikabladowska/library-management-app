package pl.sda.librarymanagementapp.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.librarymanagementapp.domain.user.Library_user;

import java.util.List;

public interface UserRepository extends JpaRepository<Library_user, Long> {

    List<Library_user> findLibrary_userByLastName(String lastName);

    Library_user findLibrary_userByEmail(String email);

    Library_user findLibrary_userByTel(Long telNumber);

}
