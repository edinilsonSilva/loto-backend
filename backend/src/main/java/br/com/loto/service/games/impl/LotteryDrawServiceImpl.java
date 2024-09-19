package br.com.loto.service.games.impl;

import br.com.loto.api.mappers.CxLotteryDrawMapper;
import br.com.loto.client.ICaixaFeign;
import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.enums.TypeGame;
import br.com.loto.domain.repository.ILotteryDrawRepository;
import br.com.loto.service.games.ILotteryDrawConsultService;
import br.com.loto.service.games.ILotteryDrawService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class LotteryDrawServiceImpl implements ILotteryDrawService {

    private final ILotteryDrawRepository lotteryDrawRepository;

    private final ILotteryDrawConsultService lotteryDrawConsultService;
    private final ICaixaFeign caixaFeign;

    @Override
    public LotteryDraw generateGame(TypeGame typeGame) {

        if (typeGame == null)
            return null;

        LotteryDraw cxLotteryDraw = CxLotteryDrawMapper.toLotteryDraw(caixaFeign.getResults(typeGame.getPathName()));

        Optional<LotteryDraw> lotteryDrawFound = lotteryDrawConsultService.findByGameTypeAndNumber(typeGame, cxLotteryDraw.getNumber());

        if (lotteryDrawFound.isPresent()) {

            LotteryDraw ldTempUpdate = cxLotteryDraw;
            ldTempUpdate.setId(lotteryDrawFound.get().getId());
            return lotteryDrawRepository.save(ldTempUpdate);
        }

        return lotteryDrawRepository.save(cxLotteryDraw);
    }

    @Override
    public void updateGameResults() {
        for (TypeGame typeGame : TypeGame.values()) {
            generateGame(typeGame);
            log.info("Atualização jogo " + typeGame.getDescription() + " as " + LocalDateTime.now());
        }
    }
}
