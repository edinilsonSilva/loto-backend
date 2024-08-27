package br.com.loto.service;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.AccountPassword;

public interface IAccountPasswordService {

    AccountPassword saveAndFlush(AccountPassword password);

    AccountPassword create(String password, Account account);
}
