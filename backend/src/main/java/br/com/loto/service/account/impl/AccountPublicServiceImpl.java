package br.com.loto.service.account.impl;

import br.com.loto.api.dto.account.requests.ChangePasswordPublicRequest;
import br.com.loto.api.dto.account.requests.CreateAccountContactRequest;
import br.com.loto.api.dto.account.requests.CreateAccountRequest;
import br.com.loto.api.dto.account.requests.ResetPasswordPublicRequest;
import br.com.loto.domain.entity.*;
import br.com.loto.domain.enums.LotteryPermission;
import br.com.loto.domain.enums.TypeContact;
import br.com.loto.domain.enums.TypeCurrency;
import br.com.loto.exceptions.AccountException;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.account.*;
import br.com.loto.service.infra.IEmailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPublicServiceImpl implements IAccountPublicService {

    private final IAccountService accountService;
    private final IAccountConfigService configService;
    private final IAccountLotteryService accountLotteryService;
    private final IAccountLotteryWalletService accountLotteryWalletService;
    private final IAccountContactService contactService;
    private final IAccountPasswordService passwordService;
    private final IEmailService emailService;

    final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void resetPassword(ResetPasswordPublicRequest request) {

        Account account = accountService.findByCpfWithThrow(request.getCpf());

        AccountPassword requestNewPassword = account.getPasswords().stream()
                .filter(p -> p.getTokenForgetPassword() != null)
                .filter(p -> !p.isActive())
                .filter(p -> p.getExpiredAt() == null)
                .findFirst()
                .orElse(null);

        if (requestNewPassword == null)
            requestNewPassword = passwordService.createRequestNewPassword(null, account);
        else
            requestNewPassword = passwordService.createRequestNewPassword(requestNewPassword, account);

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("name", account.getName());
        templateModel.put("resetLink", "http://localhost:3000/alterar-senha?token=" + requestNewPassword.getTokenForgetPassword());

        String email = account.getContacts().stream()
                .filter(c -> c.getType().equals(TypeContact.EMAIL))
                .filter(c -> c.getValue() != null)
                .map(c -> c.getValue())
                .findFirst()
                .orElseThrow(() -> new AccountException("Esta conta não possui um e-mail cadastrado. por favor, entre em contato com o suporte para a resolução do seu problema.", 404));

        emailService.sendPasswordResetEmail(email, "Redefina sua senha", templateModel);


        //        Account accountBanco = pessoaRepository.findByEmailAndCodigoRecuperacaoSenha(account.getEmail(),
//                account.getCodigoRecuperacaoSenha());
//        if (accountBanco != null) {
//            Date diferenca = new Date(new Date().getTime() - accountBanco.getDataEnvioCodigo().getTime());
//            if (diferenca.getTime() / 1000 < 900) {
//                //depois que adicionar o spring security é necessário criptografar a senha!!
//                accountBanco.setSenha(passwordEncoder.encode(account.getSenha()));
//                accountBanco.setCodigoRecuperacaoSenha(null);
//                pessoaRepository.saveAndFlush(accountBanco);
//                return "Senha alterada com sucesso!";
//            } else {
//                return "Tempo expirado, solicite um novo código";
//            }
//        } else {
//            return "Email ou código não encontrado!";
//        }
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordPublicRequest request) {

        AccountPassword accountPassword = passwordService.update(request);
        Account account = accountPassword.getAccount();

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("name", account.getName());
        templateModel.put("info", "Sua senha foi alterada as " + accountPassword.getUpdatedAt().toString() + ".");

        String email = account.getContacts().stream()
                .filter(c -> c.getType().equals(TypeContact.EMAIL))
                .filter(c -> c.getValue() != null)
                .map(c -> c.getValue())
                .findFirst()
                .orElseThrow(() -> new AccountException("Esta conta não possui um e-mail cadastrado. por favor, entre em contato com o suporte para a resolução do seu problema.", 404));

        emailService.sendPasswordResetEmail(email, "Senha alterada.", templateModel);
    }

    @Override
    @Transactional
    public CustomResponse<Account> create(CreateAccountRequest request) {

        AccountConfig config = configService.saveAndFlush(AccountConfig.builder()
                .active(true)
                .build());

        AccountLottery lottery = accountLotteryService.saveAndFlush(AccountLottery.builder()
                .permissions(new HashSet<>(Arrays.asList(LotteryPermission.ALLOW_CREATE_BETTING)))
                .build());

        accountLotteryWalletService.saveAndFlush(AccountLotteryWallet.builder()
                .balance(BigDecimal.valueOf(0))
                .typeCurrency(TypeCurrency.BRL)
                .accountLottery(lottery)
                .build());

        Account account = accountService.saveAndFlush(Account.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .config(config)
                .accountLottery(lottery)
                .build());

        for (CreateAccountContactRequest req : request.getContacts())
            contactService.saveAndFlush(AccountContact.builder()
                    .value(req.getValue())
                    .type(req.getType())
                    .account(account)
                    .build());

        passwordService.createWithOnlyPassword(request.getPassword(), account);

        return CustomResponse.<Account>builder()
                .status(201)
                .message("Conta cadastrada com sucesso.")
                .build();
    }
}
