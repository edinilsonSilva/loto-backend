package br.com.loto.service.impl;

import br.com.loto.domain.entity.AccountRole;
import br.com.loto.domain.repository.IAccountRoleRepository;
import br.com.loto.service.IAccountRoleService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountRoleServiceImpl implements IAccountRoleService {

    private final IAccountRoleRepository roleRepository;

    @Override
    @Transactional
    public AccountRole saveAndFlush(AccountRole role) {
        return roleRepository.saveAndFlush(role);
    }
}
