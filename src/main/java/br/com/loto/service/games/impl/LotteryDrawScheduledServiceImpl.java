package br.com.loto.service.games.impl;

import br.com.loto.service.games.ILotteryDrawScheduledService;
import br.com.loto.service.games.ILotteryDrawService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
@AllArgsConstructor(onConstructor_ = @Lazy)
public class LotteryDrawScheduledServiceImpl implements ILotteryDrawScheduledService {

    private final ILotteryDrawService lotteryDrawService;
    private final String zone = "America/Sao_Paulo";

    @Override
    @Scheduled(cron = "0 0 8 * * MON-SAT", zone = zone)
    public void checkResultsAt08pm() {
        lotteryDrawService.updateGameResults();
    }

    @Override
    @Scheduled(cron = "0 27 16 * * MON-SAT", zone = zone)
    public void checkResultsAt12pm() {
        lotteryDrawService.updateGameResults();
    }

    @Override
    @Scheduled(cron = "0 0 21 * * MON-SAT", zone = zone)
    public void checkResultsAt21pm() {
        lotteryDrawService.updateGameResults();
    }

    @Override
    @Scheduled(cron = "0 0 22 * * MON-SAT", zone = zone)
    public void checkResultsAt22pm() {
        lotteryDrawService.updateGameResults();
    }

    @Override
    @Scheduled(cron = "0 0 23 * * MON-SAT", zone = zone)
    public void checkResultsAt23pm() {
        lotteryDrawService.updateGameResults();
    }

}
