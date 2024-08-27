package br.com.loto.service.impl;

import br.com.loto.domain.entity.AccountPersonal;
import br.com.loto.domain.entity.AccountRole;
import br.com.loto.domain.repository.IAccountPersonalRepository;
import br.com.loto.service.IAccountPersonalService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPersonalServiceImpl implements IAccountPersonalService {

    private final IAccountPersonalRepository personalRepository;

    @Override
    @Transactional
    public AccountPersonal saveAndFlush(AccountPersonal personal) {
        return personalRepository.saveAndFlush(personal);
    }
}
