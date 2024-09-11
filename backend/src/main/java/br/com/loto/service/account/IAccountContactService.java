package br.com.loto.service.account;

import br.com.loto.domain.entity.AccountContact;

public interface IAccountContactService {

    AccountContact saveAndFlush(AccountContact contact);
}
