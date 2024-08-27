package br.com.loto.service;

import br.com.loto.domain.entity.AccountContact;

public interface IAccountContactService {

    AccountContact saveAndFlush(AccountContact contact);
}
