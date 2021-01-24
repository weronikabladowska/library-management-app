package pl.sda.librarymanagementapp.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.librarymanagementapp.user.LibraryUser;
import pl.sda.librarymanagementapp.user.Role;
import pl.sda.librarymanagementapp.user.UserRepository;

@Component
@RequiredArgsConstructor
@Transactional
public class UserInitializer implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    @Override
    public void run(String... args) throws Exception {
        repository.save(LibraryUser.builder()
                .email("email@email.com")
                .firstName("Kasia")
                .tel(569874123L)
                .year(1997)
                .lastName("Nowak")
                .password(passwordEncoder.encode("password123"))
                .role(Role.USER).build());
        repository.save(LibraryUser.builder()
                .email("jakis@email.com")
                .firstName("Tomek")
                .tel(569874123L)
                .year(1990)
                .lastName("Klimok")
                .password(passwordEncoder.encode("password1234"))
                .role(Role.USER).build());
        repository.save(LibraryUser.builder().email("admin@admin.com").password(passwordEncoder.encode("admin123")).role(Role.ADMIN).build());
    }
}
