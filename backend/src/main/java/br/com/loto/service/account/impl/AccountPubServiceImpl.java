package br.com.loto.service.account.impl;

import br.com.loto.api.dto.account.requests.ChangePasswordPubRequest;
import br.com.loto.api.dto.account.requests.CreateAccountContactRequest;
import br.com.loto.api.dto.account.requests.CreateAccountRequest;
import br.com.loto.domain.entity.*;
import br.com.loto.enums.LotteryPermission;
import br.com.loto.enums.TypeCurrency;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.account.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPubServiceImpl implements IAccountPubService {

    private final IAccountService accountService;
    private final IAccountConfigService configService;
    private final IAccountLotteryService accountLotteryService;
    private final IAccountLotteryWalletService accountLotteryWalletService;
    private final IAccountContactService contactService;
    private final IAccountPasswordService passwordService;

    final PasswordEncoder passwordEncoder;

    @Override
    public CustomResponse<Void> changePassword(ChangePasswordPubRequest request) {

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

        return CustomResponse.<Void>builder()
                .status(200)
                .message("Senha alterada com sucesso.")
                .build();
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

        passwordService.create(request.getPassword(), account);

        return CustomResponse.<Account>builder()
                .status(201)
                .message("Conta cadastrada com sucesso.")
                .build();
    }
}
