package br.com.loto.service.games;

import br.com.loto.api.dto.game.queries.OrderQuery;
import br.com.loto.api.dto.game.response.MyOrderResponse;
import org.springframework.data.domain.Page;

public interface IMyOrderConsultService {

    Page<MyOrderResponse> findAllByParams(OrderQuery query);

}
