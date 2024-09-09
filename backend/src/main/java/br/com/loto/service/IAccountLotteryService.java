package br.com.loto.service;

import br.com.loto.domain.entity.AccountLottery;

public interface IAccountLotteryService {

    AccountLottery saveAndFlush(AccountLottery lottery);
}
