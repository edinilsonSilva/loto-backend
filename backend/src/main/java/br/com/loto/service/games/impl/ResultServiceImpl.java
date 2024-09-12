package br.com.loto.service.games.impl;

import br.com.loto.domain.entity.Award;
import br.com.loto.domain.entity.Bet;
import br.com.loto.domain.entity.Draw;
import br.com.loto.domain.repository.IAwardRepository;
import br.com.loto.domain.repository.IBetRepository;
import br.com.loto.domain.repository.IDrawRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class ResultServiceImpl {

    private final IAwardRepository awardRepository;

    private final IBetRepository betRepository;

    private final IDrawRepository drawRepository;

    public void processResults(Long drawId) {
        Draw draw = drawRepository.findById(drawId)
                .orElseThrow(() -> new RuntimeException("Draw not found"));

        List<Bet> bets = betRepository.findAll();

        for (Bet bet : bets) {
            int matches = countMatches(bet.getChosenNumbers(), draw.getWinningNumbers());

            Award result = new Award();
            result.setBet(bet);
            result.setDraw(draw);
            result.setNumberOfMatches(matches);

            awardRepository.save(result);
        }
    }

    private int countMatches(List<Integer> chosenNumbers, List<Integer> winningNumbers) {
        int matches = 0;
        for (Integer number : chosenNumbers) {
            if (winningNumbers.contains(number)) {
                matches++;
            }
        }
        return matches;
    }
}
