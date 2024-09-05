package br.com.loto.service;

import br.com.loto.api.dto.requests.LoginRequest;
import br.com.loto.api.dto.responses.AccountResponse;
import br.com.loto.api.dto.responses.LoginResponse;

public interface IAccountAuthService {

    LoginResponse login (LoginRequest request);

    AccountResponse getAccount ();
}
