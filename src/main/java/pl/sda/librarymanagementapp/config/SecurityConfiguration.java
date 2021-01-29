package pl.sda.librarymanagementapp.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import pl.sda.librarymanagementapp.user.CustomUserDetailsService;

@Component

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/books?title=**").permitAll()
//                .antMatchers("/books?author=**").permitAll()
//                .antMatchers("/rents/delayed").hasRole("ADMIN")
//                .antMatchers("/rents/active").hasRole("ADMIN")
//                .antMatchers("/rents?bookId=**").hasRole("ADMIN")
//                .antMatchers("/rents?rentId=**").hasRole("ADMIN")
//                .antMatchers("/rents/create").hasRole("USER")
//                .antMatchers("/address").hasRole("USER")
//                .antMatchers("/address/street/**").hasRole("ADMIN")
//                .antMatchers("/users/page**").hasRole("ADMIN")
//                .antMatchers("/users/lastName/**").hasRole("ADMIN")
//                .antMatchers("/users/email/**").hasRole("ADMIN")
//                .antMatchers("/users/tel/**").hasRole("ADMIN")
//                .antMatchers("/login", "/h2").permitAll()
//                .and()
//                .httpBasic()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();


    }
}
