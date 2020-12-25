package pl.sda.librarymanagementapp.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.librarymanagementapp.domain.user.Library_user;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Library_user, Long> {

    public List<Library_user> findLibrary_userByLastName(String lastName);

    public Library_user findLibrary_userByEmail(String email);

    public Library_user findLibrary_userByTel(Long telNumber);

}
