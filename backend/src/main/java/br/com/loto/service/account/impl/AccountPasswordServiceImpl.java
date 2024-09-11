package br.com.loto.service.account.impl;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.AccountPassword;
import br.com.loto.domain.repository.IAccountPasswordRepository;
import br.com.loto.service.account.IAccountPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPasswordServiceImpl implements IAccountPasswordService {

    private final IAccountPasswordRepository passwordRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AccountPassword saveAndFlush(AccountPassword password) {
        return passwordRepository.saveAndFlush(password);
    }

    @Override
    public AccountPassword create(String password, Account account) {
        return saveAndFlush(AccountPassword.builder()
                .password(passwordEncoder.encode(password))
                .active(true)
                .samePasswordLimit(0L)
                .account(account)
                .build());
    }
}
