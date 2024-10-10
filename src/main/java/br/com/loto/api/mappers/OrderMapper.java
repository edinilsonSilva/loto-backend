
package br.com.loto.api.mappers;

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

    public OrderResponse convertEntityToResponse(Order order) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(order, OrderResponse.class);
    }
}
