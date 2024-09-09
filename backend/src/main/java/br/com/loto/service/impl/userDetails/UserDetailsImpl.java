package br.com.loto.service.impl.userDetails;

import br.com.loto.domain.entity.Account;
import lombok.Getter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class UserDetailsImpl implements UserDetails {

    private Account account; // Classe de usuário que criamos anteriormente

    public UserDetailsImpl(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> permissions = new ArrayList<>();

        if (account.getAccountAdmin() != null)
            permissions.addAll(account.getAccountAdmin().getPermissions()
                    .stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.name()))
                    .toList());

        if (account.getAccountLottery() != null)
            permissions.addAll(account.getAccountLottery().getPermissions()
                    .stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.name()))
                    .toList());

        return permissions;
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
        return account.getCpf();
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
