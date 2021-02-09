package pl.sda.librarymanagementapp.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<LibraryUser, Long> {

    List<LibraryUser> findLibrary_userByLastName(String lastName);

    LibraryUser findLibrary_userByEmail(String email);

    List<LibraryUser> findLibrary_userByTel(Long telNumber);

}
