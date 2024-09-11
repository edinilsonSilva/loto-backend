package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.PoolQuery;
import br.com.loto.api.dto.game.request.CreatePoolRequest;
import br.com.loto.domain.entity.Pool;
import br.com.loto.domain.repository.IPoolRepository;
import br.com.loto.enums.PoolStatus;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.games.IGameService;
import br.com.loto.service.games.IPoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PoolServiceImpl implements IPoolService {

    private final IPoolRepository poolRepository;

    private final IGameService gameService;

    @Override
    public Page<Pool> findAllByParams(PoolQuery query) {
        return null;
    }

    @Override
    @Transactional
    public CustomResponse<Pool> create(CreatePoolRequest request) {
        return CustomResponse.<Pool>builder()
                .status(201)
                .message("Bolão cadastrado.")
                .content(save(Pool.builder()
                        .createdAt(LocalDateTime.now())
                        .createdAt(null)
                        .name(request.getName())
                        .totalAmount(request.getTotalAmount())
                        .status(PoolStatus.FECHADO)
                        .game(gameService.findByIdWithThrow(request.getGameId()))
                        .build()))
                .build();
    }

    @Override
    @Transactional
    public Pool save(Pool pool) {
        return poolRepository.save(pool);
    }
}
