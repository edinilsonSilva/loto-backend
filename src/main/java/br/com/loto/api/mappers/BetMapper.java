package br.com.loto.api.mappers;

import br.com.loto.api.dto.game.response.BetResponse;
import br.com.loto.domain.entity.Bet;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * <p>
 * DATA CRIACAO: 10/09/2024
 */

@Component
@AllArgsConstructor(onConstructor_ = @Lazy)
public class BetMapper {

    private final ModelMapper modelMapper;

    public BetResponse convertEntityToResponse(Bet bet) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(bet, BetResponse.class);
    }
}
