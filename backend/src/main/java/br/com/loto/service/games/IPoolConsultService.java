package br.com.loto.service.games;

import br.com.loto.api.dto.game.queries.PoolQuery;
import br.com.loto.api.dto.game.response.PoolResponse;
import br.com.loto.domain.entity.Pool;
import org.springframework.data.domain.Page;

public interface IPoolConsultService {

    Page<PoolResponse> findAllByParams(PoolQuery query);

    Pool findByIdWithThow (Long poolId);
}
