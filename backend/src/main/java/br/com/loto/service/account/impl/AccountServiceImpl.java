package br.com.loto.service.account.impl;

import br.com.loto.api.dto.account.query.AccountQuery;
import br.com.loto.config.security.JwtUtil;
import br.com.loto.domain.entity.Account;
import br.com.loto.domain.repository.IAccountRepository;
import br.com.loto.domain.specification.AccountSpecification;
import br.com.loto.exceptions.AccountException;
import br.com.loto.service.account.IAccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;

    private final JwtUtil jwtUtil;
    private final HttpServletRequest httpRequest;

    @Override
    @Transactional
    public Account saveAndFlush(Account account) {
        return accountRepository.saveAndFlush(account);
    }

    @Override
    public Account findByCpfWithThrow(String username) {
        return accountRepository.findByCpf(username)
                .orElseThrow(() -> new AccountException("Conta não encontrada", 404));
    }

    @Override
    public Account findByIdWithThrow(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Conta não encontrada", 404));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Account findUserCurrent() {
        return findByCpfWithThrow(jwtUtil.getUsername(httpRequest.getHeader("authorization")));
    }

    @Override
    public Page<Account> findAllByParams(AccountQuery query) {

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));

        return accountRepository.findAll(AccountSpecification.search(query), pageRequest);
        //return accountPage.map(account -> simpleMapper.convertEntityToSchResponse(account));
    }
}
