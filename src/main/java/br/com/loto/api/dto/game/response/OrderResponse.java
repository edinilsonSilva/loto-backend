package br.com.loto.api.dto.game.response;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.OrderItem;
import br.com.loto.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<OrderItemResponse> items;

    private OrderStatus status;

    private Integer chargeId;

    private String transactionId;

    private String paymentMethod;

    private String amount;

}