package br.com.loto.api.dto.game.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrizeBreakdownPublicResponse {

    private Long id;

    private String prizeTierDescription;

    private int tier;

    private int numberOfWinners;

    private String prizeAmount;
}
