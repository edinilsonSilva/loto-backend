package br.com.loto.service;

import br.com.loto.domain.entity.Account;

public interface IAccountService {

    Account saveAndFlush(Account account);

    Account findByUsernameWithThrow (String username);
}
