package br.com.loto.api.mappers;

import br.com.loto.api.dto.game.response.PoolResponse;
import br.com.loto.domain.entity.Pool;
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
public class PoolMapper {

    private final ModelMapper modelMapper;

    public PoolResponse convertEntityToResponse(Pool pool) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PoolResponse response = modelMapper.map(pool, PoolResponse.class);
        response.setStatus(pool.getStatus().getDescription());
        response.setLotteryDraw(pool.getLotteryDraw().getGameType().getDescription());
        return response;
    }
}
