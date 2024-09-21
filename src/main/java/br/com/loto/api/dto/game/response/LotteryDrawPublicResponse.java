package br.com.loto.api.dto.game.response;

import br.com.loto.domain.enums.TypeGame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LotteryDrawPublicResponse {

    private Long id;

    private TypeGame gameType;

    private boolean accumulated;

    private LocalDate drawDate;

    private LocalDate nextDrawDate;

    private List<String> drawNumbers;

    private List<PrizeBreakdownPublicResponse> prizeBreakdownList;

    private int number;

    private int previousDrawNumber;

    private int nextDrawNumber;

    private boolean lastDraw;

    private String collectedAmount;

    private String estimatedNextDrawAmount;

}