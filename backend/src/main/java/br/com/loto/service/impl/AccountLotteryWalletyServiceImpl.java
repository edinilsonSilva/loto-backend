package br.com.loto.service.impl;

import br.com.loto.domain.entity.AccountLotteryWallet;
import br.com.loto.domain.repository.IAccountLotteryWalletRepository;
import br.com.loto.service.IAccountLotteryWalletService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountLotteryWalletyServiceImpl implements IAccountLotteryWalletService {

    private final IAccountLotteryWalletRepository accountLotteryWalletRepository;

    @Override
    @Transactional
    public AccountLotteryWallet saveAndFlush(AccountLotteryWallet wallet) {
        return accountLotteryWalletRepository.saveAndFlush(wallet);
    }
}
