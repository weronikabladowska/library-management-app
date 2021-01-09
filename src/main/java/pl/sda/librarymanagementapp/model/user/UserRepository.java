package pl.sda.librarymanagementapp.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.librarymanagementapp.domain.user.LibraryUser;

import java.util.List;

public interface UserRepository extends JpaRepository<LibraryUser, Long> {

    List<LibraryUser> findLibrary_userByLastName(String lastName);

    LibraryUser findLibrary_userByEmail(String email);

    LibraryUser findLibrary_userByTel(Long telNumber);

}
