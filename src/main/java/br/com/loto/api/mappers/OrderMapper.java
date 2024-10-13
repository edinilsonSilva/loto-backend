
package br.com.loto.api.mappers;

import br.com.loto.api.dto.game.response.MyOrderResponse;
import br.com.loto.api.dto.game.response.OrderResponse;
import br.com.loto.domain.entity.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * <p>
 * DATA CRIACAO: 10/09/2024
 */

@Component
@AllArgsConstructor(onConstructor_ = @Lazy)
public class OrderMapper {

    private final ModelMapper modelMapper;

    public MyOrderResponse convertEntityToMyResponse(Order order) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(order, MyOrderResponse.class);
    }

    public OrderResponse convertEntityToResponse(Order order) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderResponse response = modelMapper.map(order, OrderResponse.class);
        response.setName(order.getAccount().getName());
        response.setCpf(order.getAccount().getCpf());
        response.setEmail(null);
        return response;
    }
}
