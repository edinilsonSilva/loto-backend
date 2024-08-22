package br.com.loto.service;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.AccountPassword;
import br.com.loto.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Pessoa não encontrada pelo email"));

        Optional<AccountPassword> pass = account.getPasswords()
                .stream()
                .filter(p -> p.isActive())
                .findFirst();

        if (!pass.isPresent())
            throw new BadCredentialsException("Não há senha ativa. contacte com o administrador do sistema.");

        if (account.getConfig() == null)
            throw new BadCredentialsException("Sua conta não possui configurações. contacte com o administrador do sistema.");

        if (!account.getConfig().isActive())
            throw new BadCredentialsException("Sua conta foi desativada pela administração.");

        return new User(
                account.getUsername(),
                pass.get().getPassword(),
                getTypeUsers(account)
        );
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<GrantedAuthority> getTypeUsers(Account account) {

        List<GrantedAuthority> authorites = new ArrayList<GrantedAuthority>();

        List<String> permissions = account.getPermissions().stream()
                .map(p -> p.getPermission().getName())
                .toList();

        authorites.addAll(permissions.stream()
                .map(p -> new SimpleGrantedAuthority("ROLE_".concat(p)))
                .toList()
        );

        return authorites;
    }
}
