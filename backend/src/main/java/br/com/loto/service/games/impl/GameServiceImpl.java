package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.GameQuery;
import br.com.loto.api.dto.game.request.CreateGameRequest;
import br.com.loto.domain.entity.Contest;
import br.com.loto.domain.entity.Game;
import br.com.loto.domain.repository.IGameRepository;
import br.com.loto.domain.specification.GameSpecification;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class GameServiceImpl implements IGameService {

    private final IGameRepository gameRepository;

    private final IContestService contestService;

    @Override
    public Page<Game> findAllByParams(GameQuery query) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));

        return gameRepository.findAll(GameSpecification.search(query), pageRequest);
    }

    @Override
    @Transactional
    public CustomResponse<Game> create(CreateGameRequest request) {

        Game game = save(Game.builder()
                .createdAt(LocalDateTime.now())
                .createdAt(null)
                .name(request.getName())
                .maxNumbers(request.getMaxNumber())
                .minNumbers(request.getMinNumber())
                .maxNumberValue(request.getMaxNumberValue())
                .build());

        contestService.save(Contest.builder()
                .contestNumber(request.getContestNumber())
                .prizeAmount(request.getPrizeAmount())
                .game(game)
                .drawDate(LocalDate.parse(request.getDrawDate()))
                .build());

        return CustomResponse.<Game>builder()
                .status(201)
                .message("Jogo cadastrado.")
                .content(game)
                .build();
    }

    @Override
    @Transactional
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game findByIdWithThrow(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado."));
    }
}
