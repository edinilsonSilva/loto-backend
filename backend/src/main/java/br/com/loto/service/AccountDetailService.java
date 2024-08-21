package br.com.loto.service;

import br.com.loto.entity.Account;
import br.com.loto.entity.AccountPassword;
import br.com.loto.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
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
public class AccountDetailService implements UserDetailsService{

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
            throw new BadCredentialsException("Não há sendddha ativa. contacte com o administrador do sistema.");

        if (account.isActive() == false)
            throw new BadCredentialsException("Sua conta foi desativada pela administração.");

        return new User(
                account.getUsername(),
                pass.get().getPassword(),
                getTypeUsers(account)
        );
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<GrantedAuthority> getTypeUsers (Account account) {

        List<GrantedAuthority> authorites = new ArrayList<GrantedAuthority>();

//        if (account.getAdminId() != null)
//            authorites.add(new SimpleGrantedAuthority("ROLE_".concat(Account.TypeUser.ACCOUNT_ADMIN.name())));
//
//        if (account.getSimpleId() != null)
//            authorites.add(new SimpleGrantedAuthority("ROLE_".concat(Account.TypeUser.ACCOUNT_SIMPLE.name())));
//
//        if (account.getDeveloperId() != null)
//            authorites.add(new SimpleGrantedAuthority("ROLE_".concat(Account.TypeUser.ACCOUNT_DEVELOPER.name())));
//
//        if (account.getInstanceId() != null)
//            authorites.add(new SimpleGrantedAuthority("ROLE_".concat(Account.TypeUser.ACCOUNT_INSTANCE.name())));

        return authorites;
    }
}
