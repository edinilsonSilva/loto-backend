package br.com.loto.service.account;

import br.com.loto.domain.entity.AccountLotteryWallet;

public interface IAccountLotteryWalletService {

    AccountLotteryWallet saveAndFlush(AccountLotteryWallet lottery);
}
