package com.example.bukkinyulszbackend.security.data;

import com.example.bukkinyulszbackend.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
@Getter
@Setter
@ToString
public class CustomUserDetails extends User implements UserDetails {
    @Serial
    private static final long serialVersionUID = 6395307254648047533L;
    private final Collection<? extends GrantedAuthority> authorities;

    private CustomUserDetails(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


    public CustomUserDetails(Long id, String username, String password, String displayName,  String email, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(id, username, enabled,  email, password, displayName);
        this.authorities = authorities;

    }

    public static CustomUserDetails build(User account) {
        List<GrantedAuthority> authorities = account.getPermission().stream()
                .map(role -> new SimpleGrantedAuthority(role.getPermission()))
                .collect(Collectors.toList());
        return new CustomUserDetails(
                account.getId(),
                account.getUsername(),
                account.getPassword(),
                account.getDisplayName(),
                account.getEmail(),
                account.getEnabled(),
                authorities);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
}
