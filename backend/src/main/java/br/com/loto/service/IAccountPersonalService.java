package br.com.loto.service;

import br.com.loto.domain.entity.AccountPersonal;

public interface IAccountPersonalService {

    AccountPersonal saveAndFlush(AccountPersonal personal);
}
