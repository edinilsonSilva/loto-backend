package br.com.loto.service;

import br.com.loto.domain.entity.AccountRole;

public interface IAccountRoleService {

    AccountRole saveAndFlush(AccountRole role);
}
