package br.com.loto.service.impl;

import br.com.loto.domain.repository.IAccountPersonalRepository;
import br.com.loto.service.IAccountPersonalService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPersonalServiceImpl implements IAccountPersonalService {

    private final IAccountPersonalRepository personalRepository;
}
