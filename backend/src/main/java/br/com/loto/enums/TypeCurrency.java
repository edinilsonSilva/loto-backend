package br.com.loto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeCurrency {

	BRL("Real"),
	USD("Dolar"),
	EUR("Euro");

	private String description;

}
