package br.com.loto.service.account;

import br.com.loto.api.dto.account.query.AccountQuery;
import br.com.loto.domain.entity.Account;
import org.springframework.data.domain.Page;

public interface IAccountService {

    Account saveAndFlush(Account account);

    Account findByCpfWithThrow (String username);

    Account findByIdWithThrow (Long accountId);

    Account findAccountCurrent();

    Page<Account> findAllByParams (AccountQuery query);
}
