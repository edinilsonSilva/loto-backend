package br.com.loto.service.account.impl;

import br.com.loto.api.dto.account.requests.ChangePasswordPublicRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.AccountPassword;
import br.com.loto.domain.repository.IAccountPasswordRepository;
import br.com.loto.exceptions.AccountException;
import br.com.loto.service.account.IAccountPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPasswordServiceImpl implements IAccountPasswordService {

    private final IAccountPasswordRepository passwordRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AccountPassword saveAndFlush(AccountPassword password) {
        return passwordRepository.saveAndFlush(password);
    }

    @Override
    public AccountPassword createRequestNewPassword(AccountPassword accountPasswordOld, Account account) {
        return saveAndFlush(AccountPassword.builder()
                //.password(passwordEncoder.encode(password))
                .id(accountPasswordOld == null ? null : accountPasswordOld.getId())
                .active(false)
                .samePasswordLimit(0L)
                .createPasswordNextLogin(false)
                .tokenForgetPassword(UUID.randomUUID().toString())
                .account(account)
                .build());
    }

    @Override
    public AccountPassword createWithOnlyPassword(String password, Account account) {
        return saveAndFlush(AccountPassword.builder()
                .password(passwordEncoder.encode(password))
                .active(false)
                .samePasswordLimit(0L)
                .createPasswordNextLogin(false)
                .account(account)
                .build());
    }

    @Override
    public AccountPassword findByTokenForgotPassword(String tokenForgotPassword) {
        return passwordRepository.findByTokenForgetPassword(tokenForgotPassword)
                .orElseThrow(() -> new AccountException("Token inválido.", 400));
    }

    @Override
    public AccountPassword update(ChangePasswordPublicRequest request) {

        AccountPassword accountPassword = findByTokenForgotPassword(request.getToken());

        for (AccountPassword pw : accountPassword.getAccount().getPasswords()) {
            if (pw.isActive() && pw.getExpiredAt() == null) {
                pw.setActive(false);
                pw.setExpiredAt(LocalDateTime.now());
                saveAndFlush(pw);
            }
        }

        accountPassword.setPassword(passwordEncoder.encode(request.getNewPassword()));
        accountPassword.setActive(true);
        accountPassword = saveAndFlush(accountPassword);

        return accountPassword;
    }
}
