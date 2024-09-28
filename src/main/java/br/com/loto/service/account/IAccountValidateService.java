package br.com.loto.service.account;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.AccountAdmin;
import br.com.loto.domain.enums.AdminPermission;

public interface IAccountValidateService {

    void veriftPermission(AdminPermission adminPermission, AccountAdmin accountAdmin);

    Account verifyIfAccountCurrenIsAdmin();
}
