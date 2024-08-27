package br.com.loto.service;

import br.com.loto.domain.entity.AccountConfig;

public interface IAccountConfigService {

    AccountConfig saveAndFlush(AccountConfig config);
}
