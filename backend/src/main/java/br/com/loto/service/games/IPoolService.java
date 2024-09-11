package br.com.loto.service.games;

import br.com.loto.api.dto.game.queries.PoolQuery;
import br.com.loto.api.dto.game.request.CreatePoolRequest;
import br.com.loto.domain.entity.Pool;
import br.com.loto.exceptions.CustomResponse;
import org.springframework.data.domain.Page;

public interface IPoolService {

    Page<Pool> findAllByParams(PoolQuery query);

    CustomResponse<Pool> create(CreatePoolRequest request);

    Pool save(Pool pool);
}
