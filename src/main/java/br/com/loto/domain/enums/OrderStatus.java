package br.com.loto.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * @date 09/10/2024
 */

@Getter
@AllArgsConstructor
public enum OrderStatus {

    PENDING,
    PAID,
    CANCELED,
    SHIPPED,
    DELIVERED
}
