package br.com.loto.service.impl;

import br.com.loto.domain.repository.IAccountConfigRepository;
import br.com.loto.service.IAccountConfigService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountConfigServiceImpl implements IAccountConfigService {

    private final IAccountConfigRepository configRepository;
}
