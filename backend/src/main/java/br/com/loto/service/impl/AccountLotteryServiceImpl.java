package br.com.loto.service.impl;

import br.com.loto.domain.entity.AccountLottery;
import br.com.loto.domain.repository.IAccountLotteryRepository;
import br.com.loto.service.IAccountLotteryService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountLotteryServiceImpl implements IAccountLotteryService {

    private final IAccountLotteryRepository lotteryRepository;

    @Override
    @Transactional
    public AccountLottery saveAndFlush(AccountLottery lottery) {
        return lotteryRepository.saveAndFlush(lottery);
    }
}
