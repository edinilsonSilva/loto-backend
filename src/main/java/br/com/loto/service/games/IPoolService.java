package br.com.loto.service.games;

import br.com.loto.api.dto.game.request.CreatePoolRequest;
import br.com.loto.api.dto.game.request.UpdatePoolRequest;
import br.com.loto.domain.entity.Pool;

public interface IPoolService {

    Pool create(CreatePoolRequest request);

    Pool update(UpdatePoolRequest request, Long poolId);

    void deleteById(Long poolId);

    Pool save(Pool pool);
}
