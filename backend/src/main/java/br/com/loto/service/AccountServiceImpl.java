package br.com.loto.service;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountServiceImpl {

    private final AccountRepository accountRepository;

    public List<Account> buscarTodos() {
        return accountRepository.findAll();
    }

    public Account inserir(Account objeto) {
        return accountRepository.saveAndFlush(objeto);
    }

    public Account alterar(Account objeto) {
        return accountRepository.saveAndFlush(objeto);
    }

    public void excluir(Long id) {
        Account objeto = accountRepository.findById(id).get();
        accountRepository.delete(objeto);
    }
}
