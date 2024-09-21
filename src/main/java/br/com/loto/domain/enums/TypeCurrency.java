package br.com.loto.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * @date 14/08/2024
 */

@Getter
@AllArgsConstructor
public enum TypeCurrency {

	BRL("Real"),
	USD("Dolar"),
	EUR("Euro");

	private String description;

}
