package br.com.loto.api.dto.game.request;

import br.com.loto.domain.enums.PoolStatus;
import br.com.loto.domain.enums.TypeProbability;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class UpdatePoolRequest {

    private BigDecimal entryFee;

    private Integer totalShares;

    private TypeProbability probability;

    private PoolStatus status;

}
