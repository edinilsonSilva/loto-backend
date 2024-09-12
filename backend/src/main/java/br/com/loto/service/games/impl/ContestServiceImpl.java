package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.ContestQuery;
import br.com.loto.api.dto.game.request.CreateContestRequest;
import br.com.loto.domain.entity.Contest;
import br.com.loto.domain.repository.IContestRepository;
import br.com.loto.domain.specification.ContestSpecification;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.games.IContestService;
import br.com.loto.service.games.IGameService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class ContestServiceImpl implements IContestService {

    private final IContestRepository contestRepository;

    private final IGameService gameService;

    @Override
    public Page<Contest> findAllByParams(ContestQuery query) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        return contestRepository.findAll(ContestSpecification.search(query), pageRequest);
    }

    @Override
    @Transactional
    public CustomResponse<Contest> create(CreateContestRequest request) {
        return CustomResponse.<Contest>builder()
                .status(201)
                .message("Concurso cadastrado.")
                .content(save(Contest.builder()
                        .createdAt(null)
                        .contestNumber(request.getContestNumber())
                        .prizeAmount(request.getPrizeAmount())
                        .drawDate(request.getDrawDate())
                        .game(gameService.findByIdWithThrow(request.getGameId()))
                        .build()))
                .build();
    }

    @Override
    @Transactional
    public Contest save(Contest contest) {
        return contestRepository.save(contest);
    }

    @Override
    public Contest findByIdWithThrow(Long contestId) {
        return contestRepository.findById(contestId)
                .orElseThrow(() -> new RuntimeException("Concurso não encontrado."));
    }
}
