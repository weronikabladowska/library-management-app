package pl.sda.librarymanagementapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class,
		SecurityFilterAutoConfiguration.class})
public class LibraryManagementAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementAppApplication.class, args);
	}


}
