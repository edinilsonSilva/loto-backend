package br.com.loto.service;

import br.com.loto.api.dto.requests.CreateAccountRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.exceptions.CustomResponse;

public interface IAccountPubService {

    String alterarSenha(Account account);

    String solicitarCodigo(String username);

    CustomResponse<Account> create (CreateAccountRequest request);
}
