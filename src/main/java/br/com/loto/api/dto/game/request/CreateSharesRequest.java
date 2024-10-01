package br.com.loto.api.dto.game.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateSharesRequest {

    @NotNull
    private Long poolId;

}
