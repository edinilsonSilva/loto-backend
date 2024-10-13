package br.com.loto.service.games;

import br.com.loto.api.dto.game.queries.OrderQuery;
import br.com.loto.api.dto.game.response.MyOrderResponse;
import br.com.loto.api.dto.game.response.OrderResponse;
import org.springframework.data.domain.Page;

public interface IOrderConsultService {

    Page<OrderResponse> findAllByParams(OrderQuery query);

}
