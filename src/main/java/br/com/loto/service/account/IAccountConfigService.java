package br.com.loto.service.account;

import br.com.loto.domain.entity.AccountConfig;

public interface IAccountConfigService {

    AccountConfig saveAndFlush(AccountConfig config);
}
