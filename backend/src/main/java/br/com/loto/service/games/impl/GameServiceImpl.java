package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.GameQuery;
import br.com.loto.api.dto.game.request.CreateGameRequest;
import br.com.loto.domain.entity.Game;
import br.com.loto.domain.repository.IGameRepository;
import br.com.loto.domain.specification.GameSpecification;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.games.IGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements IGameService {

    private final IGameRepository gameRepository;

    @Override
    public Page<Game> findAllByParams(GameQuery query) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));

        return gameRepository.findAll(GameSpecification.search(query), pageRequest);
    }

    @Override
    @Transactional
    public CustomResponse<Game> create(CreateGameRequest request) {
        return CustomResponse.<Game>builder()
                .status(201)
                .message("Jogo cadastrado.")
                .content(save(Game.builder()
                        .createdAt(LocalDateTime.now())
                        .createdAt(null)
                        .name(request.getName())
                        .maxNumbers(request.getMaxNumber())
                        .minNumbers(request.getMinNumber())
                        .maxNumberValue(request.getMaxNumberValue())
                        .build()))
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
