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
//todo - antmatchery, ktore sciezki jaka role maja miejsc
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/**").hasRole("USER")
                .anyRequest().permitAll()
//                .antMatchers("/rents/delayed").hasRole("USER")
//                .antMatchers("/login", "/h2").permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();


    }
}
