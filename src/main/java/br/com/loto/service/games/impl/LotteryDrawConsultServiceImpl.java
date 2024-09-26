package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.ContestQuery;
import br.com.loto.api.dto.game.response.LotteryDrawPublicResponse;
import br.com.loto.api.dto.game.response.LotteryDrawReduced01Response;
import br.com.loto.api.mappers.LotteryDrawMapper;
import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.enums.TypeGame;
import br.com.loto.domain.repository.ILotteryDrawRepository;
import br.com.loto.domain.specification.LotteryDrawSpecification;
import br.com.loto.exceptions.AccountException;
import br.com.loto.service.account.IAccountService;
import br.com.loto.service.games.ILotteryDrawConsultService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class LotteryDrawConsultServiceImpl implements ILotteryDrawConsultService {

    private final ILotteryDrawRepository lotteryDrawRepository;

    private final LotteryDrawMapper lotteryDrawMapper;
    private final IAccountService accountService;

    @Override
    public Page<LotteryDraw> findAllByParams(ContestQuery query) {

//        Account accountCurrent = accountService.findAccountCurrent();
//
//        if (accountCurrent.getAccountAdmin() == null)
//            throw new AccountException("Sua conta não tem permissão para acessar este recurso.", 4003);

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        return lotteryDrawRepository.findAll(LotteryDrawSpecification.search(query), pageRequest);
    }

    @Override
    public Page<LotteryDrawReduced01Response> findAllReduced01(ContestQuery query) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        Page<LotteryDraw> pages = lotteryDrawRepository.findAll(LotteryDrawSpecification.search(query), pageRequest);
        return pages.map(lotteryDrawMapper::convertEntityToReduced01Response);
    }

    @Override
    public Page<LotteryDrawPublicResponse> findAllByParamsPublic(ContestQuery query) {
        return findAllByParams(query).map(lotteryDrawMapper::convertEntityToPublicResponse);
    }

    @Override
    public LotteryDraw findByIdWithThrow(Long contestId) {
        return lotteryDrawRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("Concurso não encontrado."));
    }

    @Override
    public Optional<LotteryDraw> findByGameTypeAndNumber(TypeGame typeGame, int number) {
        return lotteryDrawRepository.findByGameTypeAndNumber(typeGame, number);
    }
}
