package br.com.loto.service.account;

import br.com.loto.api.dto.account.requests.LoginRequest;
import br.com.loto.api.dto.account.responses.AccountResponse;
import br.com.loto.api.dto.account.responses.LoginResponse;

public interface IAccountAuthService {

    LoginResponse login (LoginRequest request);

    AccountResponse getAccount ();
}
