package br.com.loto.api.dto.game.queries;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PoolQuery {

    private Integer page;

    private Integer limit;

    private String orderBy;

    private String direction;

    private String name;

}