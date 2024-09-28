package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.BetQuery;
import br.com.loto.api.dto.game.response.BetResponse;
import br.com.loto.api.mappers.BetMapper;
import br.com.loto.domain.entity.Bet;
import br.com.loto.domain.repository.IBetRepository;
import br.com.loto.domain.specification.BetSpecification;
import br.com.loto.exceptions.BetException;
import br.com.loto.service.account.IAccountValidateService;
import br.com.loto.service.games.IBetConsultService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class BetConsultServiceImpl implements IBetConsultService {

    private final IBetRepository betRepository;

    private final IAccountValidateService accountValidateService;

    private final BetMapper betMapper;

    @Override
    public Page<BetResponse> findAllByParams(BetQuery query) {
        accountValidateService.verifyIfAccountCurrenIsAdmin();

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        Page<Bet> pages = betRepository.findAll(BetSpecification.search(query), pageRequest);
        return pages.map(betMapper::convertEntityToResponse);
    }

    @Override
    public Page<BetResponse> findAllByParamsPublic(BetQuery query) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        Page<Bet> pages = betRepository.findAll(BetSpecification.search(query), pageRequest);
        return pages.map(betMapper::convertEntityToResponse);
    }

    @Override
    public Bet findByIdWithThow(Long betId) {
        return betRepository.findById(betId)
                .orElseThrow(() -> new BetException("Aposta não encontrada.", 4004));
    }
}
