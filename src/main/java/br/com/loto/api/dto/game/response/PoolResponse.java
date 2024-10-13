package br.com.loto.api.dto.game.response;

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
public class PoolResponse {

    private Long id;

    private String createdAt;

    private String updatedAt;

    private String createdBy;

    private String status;

    private String code;

    private Integer totalShares;

    private Integer totalSharesUse;

    private BigDecimal entryFee;

    private String probability;

    private List<PoolParticipantPublicResponse> participants;

    private List<BetResponse> bets;

    private String lotteryDraw;

    private Integer drawNumber;

    private String drawDate;

    private String collectedAmount;

}