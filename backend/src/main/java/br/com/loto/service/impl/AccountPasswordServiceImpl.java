package br.com.loto.service.impl;

import br.com.loto.domain.repository.IAccountPasswordRepository;
import br.com.loto.service.IAccountPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPasswordServiceImpl implements IAccountPasswordService {

    private final IAccountPasswordRepository passwordRepository;
}
