package br.com.loto.service.impl;

import br.com.loto.api.dto.requests.ChangePasswordRequest;
import br.com.loto.api.dto.requests.CreateAccountContactRequest;
import br.com.loto.api.dto.requests.CreateAccountRequest;
import br.com.loto.domain.entity.*;
import br.com.loto.enums.TypeRole;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPubServiceImpl implements IAccountPubService {

    private final IAccountService accountService;
    private final IAccountPersonalService personalService;
    private final IAccountConfigService configService;
    private final IAccountContactService contactService;
    private final IAccountPasswordService passwordService;
    private final IAccountRoleService roleService;

    final PasswordEncoder passwordEncoder;

    @Override
    public CustomResponse<Void> changePassword(ChangePasswordRequest request) {

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

        AccountPersonal personal = personalService.saveAndFlush(AccountPersonal.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .build());

        AccountConfig config = configService.saveAndFlush(AccountConfig.builder()
                .active(true)
                .build());

        Account account = accountService.saveAndFlush(Account.builder()
                .username(request.getUsername())
                .personal(personal)
                .config(config)
                .build());

        for (CreateAccountContactRequest req : request.getContacts())
            contactService.saveAndFlush(AccountContact.builder()
                    .value(req.getValue())
                    .type(req.getType())
                    .account(account)
                    .build());

        passwordService.create(request.getPassword(), account);

        roleService.saveAndFlush(AccountRole.builder()
                .typeRole(TypeRole.ROLE_CUSTOMER)
                .account(account)
                .build());

        return CustomResponse.<Account>builder()
                .status(201)
                .message("Conta cadastrada com sucesso.")
                .build();
    }
}
