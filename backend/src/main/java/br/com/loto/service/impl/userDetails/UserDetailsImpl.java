package br.com.loto.service.impl.userDetails;

import br.com.loto.domain.entity.Account;
import lombok.Getter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class UserDetailsImpl implements UserDetails {

    private Account account; // Classe de usuário que criamos anteriormente

    public UserDetailsImpl(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getTypeRole().name()))
                .toList();
    }

    @Override
    public String getPassword() {
        return account.getPasswords().stream()
                .filter(p -> p.isActive())
                .map(p -> p.getPassword())
                .findFirst()
                .orElseThrow(() -> new BadCredentialsException("Senha não cadastrada"));
    }

    @Override
    public String getUsername() {
        return account.getUsername();
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
}
