package br.com.loto.service.account;

import br.com.loto.api.dto.account.requests.ChangePasswordPubRequest;
import br.com.loto.api.dto.account.requests.CreateAccountRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.exceptions.CustomResponse;

public interface IAccountPubService {

    CustomResponse<Void> changePassword(ChangePasswordPubRequest request);

    CustomResponse<Account> create (CreateAccountRequest request);
}
