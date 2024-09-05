package br.com.loto.service;

import br.com.loto.api.dto.query.AccountQuery;
import br.com.loto.domain.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {

    Account saveAndFlush(Account account);

    Account findByUsernameWithThrow (String username);

    Account findByIdWithThrow (Long accountId);

    Account findUserCurrent();

    Page<Account> findAllByParams (AccountQuery query);
}
