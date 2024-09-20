package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.PoolQuery;
import br.com.loto.api.dto.game.response.PoolResponse;
import br.com.loto.api.mappers.PoolMapper;
import br.com.loto.domain.entity.Pool;
import br.com.loto.domain.repository.IPoolRepository;
import br.com.loto.domain.specification.PoolSpecification;
import br.com.loto.exceptions.PoolException;
import br.com.loto.service.games.IPoolConsultService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class PoolConsultServiceImpl implements IPoolConsultService {

    private final IPoolRepository poolRepository;

    private final PoolMapper poolMapper;

    @Override
    public Page<PoolResponse> findAllByParams(PoolQuery query) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        Page<Pool> pages = poolRepository.findAll(PoolSpecification.search(query), pageRequest);
        return pages.map(poolMapper::convertEntityToResponse);
    }

    @Override
    public Pool findByIdWithThow(Long poolId) {
        return poolRepository.findById(poolId)
                .orElseThrow(() -> new PoolException("Bolão não encontrado", 404));
    }
}
