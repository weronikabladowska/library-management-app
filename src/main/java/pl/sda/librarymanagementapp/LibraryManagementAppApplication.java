package pl.sda.librarymanagementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//		(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class,
//		SecurityFilterAutoConfiguration.class})
public class LibraryManagementAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementAppApplication.class, args);
    }


}
