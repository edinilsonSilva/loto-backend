package br.com.loto.api.dto.game.request;

import br.com.loto.domain.enums.TypeProbability;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CreatePoolRequest {

    @NotNull
    private TypeProbability probability;

    @NotNull
    private BigDecimal entryFee;

    @NotNull
    private Long lotteryDrawId;

}
