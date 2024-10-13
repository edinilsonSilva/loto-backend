package br.com.loto.api.dto.game.response;

import br.com.loto.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyOrderResponse {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<MyOrderItemResponse> items;

    private OrderStatus status;

    private Integer chargeId;

    private String transactionId;

    private String paymentMethod;

    private String amount;

}