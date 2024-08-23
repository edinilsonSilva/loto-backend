package br.com.loto.service.impl;

import br.com.loto.api.dto.requests.CreateAccountRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.IAccountPubService;
import br.com.loto.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPubServiceImpl implements IAccountPubService {

    private final IAccountService accountService;

    final PasswordEncoder passwordEncoder;

    public String solicitarCodigo(String username) {
        Account account = accountService.findByUsernameWithThrow(username);

//        account.setCodigoRecuperacaoSenha(getCodigoRecuperacaoSenha(account.getId()));
//        account.setDataEnvioCodigo(new Date());
//        pessoaRepository.saveAndFlush(account);
////        emailService.enviarEmailTexto(pessoa.getEmail(), "Código de Recuperação de Senha",
//          //      "Olá, o seu código para recuperação de senha é o seguinte: " + pessoa.getCodigoRecuperacaoSenha());
        return "Código Enviado!";
    }

    public String alterarSenha(Account account) {

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

        return "";
    }

    private String getCodigoRecuperacaoSenha(Long id) {
        DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return format.format(new Date()) + id;
    }

    @Override
    @Transactional
    public CustomResponse<Account> create(CreateAccountRequest request) {
        Account account = accountService.saveAndFlush(null);
        return CustomResponse.<Account>builder()
                .status(201)
                .message("Conta cadastrada com sucesso.")
                .data(account)
                .build();
    }
}
