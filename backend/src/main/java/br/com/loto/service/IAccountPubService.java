package br.com.loto.service;

import br.com.loto.api.dto.requests.ChangePasswordRequest;
import br.com.loto.api.dto.requests.CreateAccountRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.exceptions.CustomResponse;

public interface IAccountPubService {

    CustomResponse<Void> changePassword(ChangePasswordRequest request);

    CustomResponse<Account> create (CreateAccountRequest request);
}
