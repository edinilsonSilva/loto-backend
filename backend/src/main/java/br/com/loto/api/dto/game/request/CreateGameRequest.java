package br.com.loto.api.dto.game.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CreateGameRequest {

    private String name;

    private BigDecimal totalAmount;

    private int maxNumber;

    private int minNumber;

    private int maxNumberValue;

}
