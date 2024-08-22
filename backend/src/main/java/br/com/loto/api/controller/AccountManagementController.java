package br.com.loto.api.controller;

import br.com.loto.domain.entity.Account;
import br.com.loto.service.PessoaGerenciamentoService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts/management")
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountManagementController {

    private final PessoaGerenciamentoService pessoaGerenciamentoService;

    @PostMapping("/senha-codigo")
    public String recuperarCodigo(@RequestBody Account account) {
        return pessoaGerenciamentoService.solicitarCodigo(account.getUsername());
    }

    @PostMapping("/senha-alterar")
    public String alterarSenha(@RequestBody Account account) {
        return pessoaGerenciamentoService.alterarSenha(account);
    }

}