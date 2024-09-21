package br.com.loto.service.games;

import br.com.loto.api.dto.game.queries.ContestQuery;
import br.com.loto.api.dto.game.response.LotteryDrawPublicResponse;
import br.com.loto.api.dto.game.response.LotteryDrawReduced01Response;
import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.enums.TypeGame;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ILotteryDrawConsultService {

    Page<LotteryDraw> findAllByParams(ContestQuery query);

    Page<LotteryDrawReduced01Response> findAllReduced01(ContestQuery query);

    Page<LotteryDrawPublicResponse> findAllByParamsPublic(ContestQuery query);

    LotteryDraw findByIdWithThrow(Long contestId);

    Optional<LotteryDraw> findByGameTypeAndNumber(TypeGame typeGame, int number);

}
