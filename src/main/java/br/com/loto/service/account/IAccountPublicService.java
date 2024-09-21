package br.com.loto.service.account;

import br.com.loto.api.dto.account.requests.ChangePasswordPublicRequest;
import br.com.loto.api.dto.account.requests.CreateAccountRequest;
import br.com.loto.api.dto.account.requests.ResetPasswordPublicRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.exceptions.CustomResponse;

public interface IAccountPublicService {

    void resetPassword(ResetPasswordPublicRequest request);

    void changePassword(ChangePasswordPublicRequest request);

    CustomResponse<Account> create (CreateAccountRequest request);
}
