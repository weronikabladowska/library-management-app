package pl.sda.librarymanagementapp.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class LibraryUserAdapter implements UserDetails {

    private final LibraryUser libraryUser;

    public LibraryUserAdapter(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(new SimpleGrantedAuthority("ROLE_" + libraryUser.getRole().toString()));

    }

    @Override
    public String getPassword() {
        return libraryUser.getPassword();
    }

    @Override
    public String getUsername() {
        return libraryUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId(){
        return libraryUser.getId();
    }
}
