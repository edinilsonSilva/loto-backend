package br.com.loto.api.dto.game.queries;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderQuery {

    private Integer page;

    private Integer limit;

    private String orderBy;

    private String direction;

    private Long accountId;
}