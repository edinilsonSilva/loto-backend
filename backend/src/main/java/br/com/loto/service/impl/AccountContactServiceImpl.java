package br.com.loto.service.impl;

import br.com.loto.domain.repository.IAccountContactRepository;
import br.com.loto.service.IAccountContactService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountContactServiceImpl implements IAccountContactService {

    private final IAccountContactRepository contactRepository;
}
