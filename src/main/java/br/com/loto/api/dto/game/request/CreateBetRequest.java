package br.com.loto.api.dto.game.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CreateBetRequest {

    private List<Integer> chosenNumbers;

    @NotNull
    private Long poolId;

}
