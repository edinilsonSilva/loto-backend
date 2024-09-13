package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.PoolQuery;
import br.com.loto.api.dto.game.request.CreatePoolRequest;
import br.com.loto.domain.entity.Pool;
import br.com.loto.domain.repository.IPoolRepository;
import br.com.loto.domain.specification.PoolSpecification;
import br.com.loto.enums.PoolStatus;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.games.IContestService;
import br.com.loto.service.games.IPoolService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class PoolServiceImpl implements IPoolService {

    private final IPoolRepository poolRepository;

    private final IContestService contestService;

    @Override
    public Page<Pool> findAllByParams(PoolQuery query) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        return poolRepository.findAll(PoolSpecification.search(query), pageRequest);
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
                        .status(PoolStatus.FECHADO)
                        .entryFee(request.getEntryFee())
                        .contest(contestService.findByIdWithThrow(request.getContestId()))
                        .build()))
                .build();
    }

    @Override
    @Transactional
    public Pool save(Pool pool) {
        return poolRepository.save(pool);
    }
}
