package br.com.loto.service.account;

import br.com.loto.api.dto.account.query.AccountQuery;
import br.com.loto.domain.entity.Account;
import org.springframework.data.domain.Page;

public interface IAccountService {

    Account save(Account account);
}
