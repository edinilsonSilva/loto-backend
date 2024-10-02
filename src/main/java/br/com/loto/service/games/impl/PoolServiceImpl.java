package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.request.CreatePoolRequest;
import br.com.loto.api.dto.game.request.UpdatePoolRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.entity.Pool;
import br.com.loto.domain.enums.PoolStatus;
import br.com.loto.domain.repository.IPoolRepository;
import br.com.loto.service.account.IAccountValidateService;
import br.com.loto.service.games.ILotteryDrawConsultService;
import br.com.loto.service.games.IPoolConsultService;
import br.com.loto.service.games.IPoolService;
import br.com.loto.utils.CodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class PoolServiceImpl implements IPoolService {

    private final IPoolRepository poolRepository;

    private final ILotteryDrawConsultService lotteryDrawConsultService;
    private final IPoolConsultService poolConsultService;
    private final IAccountValidateService accountValidateService;

    @Override
    @Transactional
    public Pool create(CreatePoolRequest request) {

        Account accountCurrent = accountValidateService.verifyIfAccountCurrenIsAdmin();

        LotteryDraw lotteryDraw = lotteryDrawConsultService.findByIdWithThrow(request.getLotteryDrawId());

        return save(Pool.builder()
                .createdAt(LocalDateTime.now())
                .createdBy(accountCurrent)
                .status(PoolStatus.CLOSED)
                .code(CodeGenerator.generateCode(10))
                .probability(request.getProbability())
                .drawNumber(lotteryDraw.getNumber())
                .lotteryDraw(lotteryDraw)
                .entryFee(request.getEntryFee())
                .build());
    }

    @Override
    @Transactional
    public Pool update(UpdatePoolRequest request, Long poolId) {

        accountValidateService.verifyIfAccountCurrenIsAdmin();

        Pool pool = poolConsultService.findByIdWithThow(poolId);
        pool.setStatus(request.getStatus());
        pool.setProbability(request.getProbability());
        pool.setEntryFee(request.getEntryFee());
        return save(pool);
    }

    @Override
    @Transactional
    public void deleteById(Long poolId) {

        accountValidateService.verifyIfAccountCurrenIsAdmin();

        Pool pool = poolConsultService.findByIdWithThow(poolId);
        poolRepository.deleteById(pool.getId());
    }

    @Override
    @Transactional
    public Pool save(Pool pool) {
        return poolRepository.save(pool);
    }
}
