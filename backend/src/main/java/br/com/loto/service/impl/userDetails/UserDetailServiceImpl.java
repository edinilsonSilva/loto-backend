package br.com.loto.service.impl.userDetails;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.repository.IAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class UserDetailServiceImpl implements UserDetailsService {

    private final IAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Pessoa não encontrada pelo email"));

        return new UserDetailsImpl(account);
    }
}
