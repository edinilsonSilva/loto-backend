package br.com.loto.config.runner;

import br.com.loto.service.games.ILotteryDrawService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by Edinilson Silva - edinilson.silva@abablockchain.io;
 * 18/09/2024
 *
 * @version 1.0
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EnableCaching
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private ILotteryDrawService lotteryDrawService;

    @Override
    public void run(String... args) throws Exception {
        lotteryDrawService.updateGameResults();
    }

}