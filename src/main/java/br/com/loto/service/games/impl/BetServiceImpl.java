package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.request.CreateBetRequest;
import br.com.loto.api.dto.game.request.UpdateBetRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.Bet;
import br.com.loto.domain.entity.Pool;
import br.com.loto.domain.repository.IBetRepository;
import br.com.loto.exceptions.AccountException;
import br.com.loto.service.account.IAccountService;
import br.com.loto.service.games.IBetConsultService;
import br.com.loto.service.games.IBetService;
import br.com.loto.service.games.IPoolConsultService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class BetServiceImpl implements IBetService {

    private final IBetRepository betRepository;

    private final IBetConsultService betConsultService;
    private final IPoolConsultService poolConsultService;
    private final IAccountService accountService;

    @Override
    @Transactional
    public Bet create(CreateBetRequest request) {

        Account accountCurrent = accountService.findAccountCurrent();

        if (accountCurrent.getAccountAdmin() == null)
            throw new AccountException("Sua conta não tem permissão para acessar este recurso.", 4003);

        Pool pool = poolConsultService.findByIdWithThow(request.getPoolId());

        return save(Bet.builder()
                .chosenNumbers(request.getChosenNumbers())
                .pool(pool)
                .build());
    }

    @Override
    public Bet update(UpdateBetRequest request, Long betId) {

        Account accountCurrent = accountService.findAccountCurrent();

        if (accountCurrent.getAccountAdmin() == null)
            throw new AccountException("Sua conta não tem permissão para acessar este recurso.", 4003);

        Bet bet = betConsultService.findByIdWithThow(betId);
        Pool pool = poolConsultService.findByIdWithThow(request.getPoolId());
        bet.setChosenNumbers(request.getChosenNumbers());
        bet.setPool(pool);
        return save(bet);
    }

    @Override
    public void deleteById(Long betId) {

        Account accountCurrent = accountService.findAccountCurrent();

        if (accountCurrent.getAccountAdmin() == null)
            throw new AccountException("Sua conta não tem permissão para acessar este recurso.", 4003);

        Bet bet = betConsultService.findByIdWithThow(betId);
        betRepository.deleteById(bet.getId());
    }

    @Override
    public Bet save(Bet bet) {
        return betRepository.save(bet);
    }
}
