package br.com.loto.service.account;

import br.com.loto.api.dto.account.requests.ChangePasswordPublicRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.AccountPassword;

public interface IAccountPasswordService {

    AccountPassword saveAndFlush(AccountPassword password);

    AccountPassword createRequestNewPassword(AccountPassword accountPasswordOld, Account account);

    AccountPassword createWithOnlyPassword (String password, Account account);

    AccountPassword findByTokenForgotPassword (String tokenForgotPassword);

    AccountPassword updateByToken (ChangePasswordPublicRequest request);
}
