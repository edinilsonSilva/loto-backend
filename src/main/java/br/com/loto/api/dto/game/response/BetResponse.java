package br.com.loto.api.dto.game.response;

import br.com.loto.domain.entity.Bet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BetResponse {

    private Long id;

    private String createdAt;

    private List<Integer> chosenNumbers;

}