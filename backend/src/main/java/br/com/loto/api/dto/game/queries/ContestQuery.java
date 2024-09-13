package br.com.loto.api.dto.game.queries;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ContestQuery {

    private Integer page;

    private Integer limit;

    private String orderBy;

    private String direction;

    private Integer contestNumber;

    private Long gameId;

}