package br.com.loto.service.games;

import br.com.loto.api.dto.game.request.CreateBetRequest;
import br.com.loto.api.dto.game.request.UpdateBetRequest;
import br.com.loto.domain.entity.Bet;

public interface IBetService {

    Bet create(CreateBetRequest request);

    Bet update(UpdateBetRequest request, Long betId);

    void deleteById(Long betId);

    Bet save(Bet bet);
}
