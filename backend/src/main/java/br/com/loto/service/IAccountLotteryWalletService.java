package br.com.loto.service;

import br.com.loto.domain.entity.AccountLotteryWallet;

public interface IAccountLotteryWalletService {

    AccountLotteryWallet saveAndFlush(AccountLotteryWallet lottery);
}
