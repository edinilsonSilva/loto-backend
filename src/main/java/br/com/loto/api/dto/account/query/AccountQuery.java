package br.com.loto.api.dto.account.query;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountQuery {

    private Integer page;

    private Integer limit;

    private String orderBy;

    private String direction;

    private String name;

    private boolean active;

    private String cpf;

}