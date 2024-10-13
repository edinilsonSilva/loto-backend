package br.com.loto.api.dto.game.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyOrderItemResponse {

    private Long id;

    private PoolResponse pool;

}