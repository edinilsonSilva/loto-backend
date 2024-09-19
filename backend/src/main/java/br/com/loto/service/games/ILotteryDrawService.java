package br.com.loto.service.games;

import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.enums.TypeGame;

public interface ILotteryDrawService {

    LotteryDraw generateGame(TypeGame typeGame);

    void updateGameResults();
}
