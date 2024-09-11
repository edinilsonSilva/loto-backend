package br.com.loto.service.account;

import br.com.loto.domain.entity.AccountLottery;

public interface IAccountLotteryService {

    AccountLottery saveAndFlush(AccountLottery lottery);
}
