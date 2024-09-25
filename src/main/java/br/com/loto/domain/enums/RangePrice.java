package br.com.loto.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * @date 14/09/2024
 */

@Getter
@AllArgsConstructor
public enum RangePrice {

    R10("Até R$ 10,00", 0.0, 10.0),
    R10_20("De R$ 10,01 a R$ 20,00", 10.1, 20.0),
    R20_50("De R$ 20,01 a R$ 50,00", 20.1, 50.0),
    R50_100("De R$ 51,00 a R$ 100,00", 50.1, 100.0),
    R100("Acima de R$ 100,00", 100.1, 0.0);

    private String description;
    private Double initialValue;
    private Double finalValue;
}
