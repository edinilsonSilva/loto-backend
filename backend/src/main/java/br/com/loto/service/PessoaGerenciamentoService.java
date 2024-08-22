package br.com.loto.service;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class PessoaGerenciamentoService {

    private final AccountRepository accountRepository;

    final PasswordEncoder passwordEncoder;

    public String solicitarCodigo(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Pessoa não encontrada pelo email"));

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

}
