package br.com.loto.service.account.impl;

import br.com.loto.domain.entity.AccountConfig;
import br.com.loto.domain.repository.IAccountConfigRepository;
import br.com.loto.service.account.IAccountConfigService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountConfigServiceImpl implements IAccountConfigService {

    private final IAccountConfigRepository configRepository;

    @Override
    @Transactional
    public AccountConfig saveAndFlush(AccountConfig config) {
        return configRepository.saveAndFlush(config);
    }
}
