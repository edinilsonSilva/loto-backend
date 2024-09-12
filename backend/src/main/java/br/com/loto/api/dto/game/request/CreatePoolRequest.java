package br.com.loto.api.dto.game.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CreatePoolRequest {

    private String name;

    private BigDecimal totalAmount;

    private Long contestId;

}
