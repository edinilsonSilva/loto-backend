package br.com.loto.service.impl;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.repository.IAccountRepository;
import br.com.loto.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;

    @Override
    @Transactional
    public Account saveAndFlush(Account account) {
        return accountRepository.saveAndFlush(account);
    }

    @Override
    public Account findByUsernameWithThrow (String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Conta não encontrada"));
    }
}
