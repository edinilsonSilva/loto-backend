package br.com.loto.service.account.impl;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.repository.IAccountRepository;
import br.com.loto.service.account.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;

    @Override
    @Transactional
    public Account save(Account account) {
        return accountRepository.saveAndFlush(account);
    }

}
