package br.com.loto.service.games;

import br.com.loto.api.dto.game.queries.ContestQuery;
import br.com.loto.api.dto.game.request.CreateContestRequest;
import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.enums.TypeGame;
import br.com.loto.exceptions.CustomResponse;
import org.springframework.data.domain.Page;

public interface ILotteryDrawService {

    Page<LotteryDraw> findAllByParams(ContestQuery query);

    CustomResponse<LotteryDraw> create(CreateContestRequest request);

    LotteryDraw save(LotteryDraw contest);

    LotteryDraw findByIdWithThrow (Long contestId);

    LotteryDraw generateGame (TypeGame typeGame);
}
