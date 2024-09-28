package br.com.loto.service.account.impl;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.AccountAdmin;
import br.com.loto.domain.enums.AdminPermission;
import br.com.loto.exceptions.AccountException;
import br.com.loto.service.account.IAccountConsultService;
import br.com.loto.service.account.IAccountValidateService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountValidateServiceImpl implements IAccountValidateService {

    private final IAccountConsultService accountConsultService;

    @Override
    public void veriftPermission(AdminPermission adminPermission, AccountAdmin accountAdmin) {

    }

    @Override
    public Account verifyIfAccountCurrenIsAdmin() {
        Account accountCurrent = accountConsultService.findAccountCurrent();
        if (accountConsultService.findAccountCurrent().getAccountAdmin() == null)
            throw new AccountException("Sua conta não tem permissão para acessar este recurso.", 4003);

        return accountCurrent;
    }
}
