package br.com.loto.api.dto.game.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CreateGameRequest {

    private String name;

    private int maxNumber;

    private int minNumber;

    private int maxNumberValue;

    private Integer contestNumber;

    private BigDecimal prizeAmount;

    private String drawDate;

}
