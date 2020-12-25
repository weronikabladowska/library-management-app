package pl.sda.librarymanagementapp.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.librarymanagementapp.domain.user.Library_user;

public interface UserRepository extends JpaRepository <Library_user, Long> {
}
