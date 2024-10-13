package br.com.loto.api.mappers;

import br.com.loto.api.dto.game.response.LotteryDrawPublicResponse;
import br.com.loto.api.dto.game.response.LotteryDrawReduced01Response;
import br.com.loto.api.dto.game.response.TypeGameResponse;
import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.enums.TypeGame;
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
public class LotteryDrawMapper {

    private final ModelMapper modelMapper;

    public LotteryDrawPublicResponse convertEntityToPublicResponse(LotteryDraw lotteryDraw) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(lotteryDraw, LotteryDrawPublicResponse.class);
    }

    public LotteryDrawReduced01Response convertEntityToReduced01Response(LotteryDraw lotteryDraw) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        LotteryDrawReduced01Response response = modelMapper.map(lotteryDraw, LotteryDrawReduced01Response.class);
        response.setGame(lotteryDraw.getGameType().getDescription());
        return response;
    }

    public TypeGameResponse convertTypeGameToResponse(TypeGame typeGame) {
        return TypeGameResponse.builder()
                .name(typeGame.name())
                .description(typeGame.getDescription())
                .maxNumbers(typeGame.getMaxNumbers())
                .minNumbers(typeGame.getMinNumbers())
                .maxNumberValue(typeGame.getMaxNumberValue())
                .pathName(typeGame.getPathName())
                .build();
    }
}
