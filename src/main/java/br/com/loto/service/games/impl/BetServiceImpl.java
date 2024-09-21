package br.com.loto.service.games.impl;

import br.com.loto.domain.entity.Bet;
import br.com.loto.domain.entity.Participant;
import br.com.loto.domain.entity.Pool;
import br.com.loto.domain.repository.IBetRepository;
import br.com.loto.domain.repository.IParticipantRepository;
import br.com.loto.domain.repository.IPoolRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class BetServiceImpl {

    private final IBetRepository betRepository;

    private final IPoolRepository poolRepository;

    private final IParticipantRepository participantRepository;

    @Transactional
    public Bet placeBet(Long participantId, Long poolId, List<Integer> chosenNumbers, BigDecimal amount) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        Pool pool = poolRepository.findById(poolId)
                .orElseThrow(() -> new RuntimeException("Pool not found"));

        Bet bet = new Bet();
        bet.setParticipant(participant);
        bet.setPool(pool);
        bet.setChosenNumbers(chosenNumbers);
        bet.setAmount(amount);

        return betRepository.save(bet);
    }

    public List<Bet> getBetsByParticipant(Long participantId) {
        return betRepository.findByParticipantId(participantId);
    }
}
