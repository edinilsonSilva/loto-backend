package br.com.loto.service.games;

import br.com.loto.api.dto.game.queries.BetQuery;
import br.com.loto.api.dto.game.response.BetResponse;
import br.com.loto.domain.entity.Bet;
import org.springframework.data.domain.Page;

public interface IBetConsultService {

    Page<BetResponse> findAllByParams(BetQuery query);

    Page<BetResponse> findAllByParamsPublic(BetQuery query);

    Bet findByIdWithThow(Long betId);
}
