package br.com.loto.api.dto.game.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
public class CreateContestRequest {

    @NotNull
    private Integer contestNumber;

    @NotNull
    private BigDecimal prizeAmount;

    @NotNull
    private LocalDate drawDate;

    @NotNull
    private Long gameId;

}
