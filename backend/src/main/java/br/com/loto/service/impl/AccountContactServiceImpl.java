package br.com.loto.service.impl;

import br.com.loto.domain.entity.AccountContact;
import br.com.loto.domain.entity.AccountRole;
import br.com.loto.domain.repository.IAccountContactRepository;
import br.com.loto.service.IAccountContactService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountContactServiceImpl implements IAccountContactService {

    private final IAccountContactRepository contactRepository;

    @Override
    @Transactional
    public AccountContact saveAndFlush(AccountContact contact) {
        return contactRepository.saveAndFlush(contact);
    }
}
