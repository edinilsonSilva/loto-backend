package br.com.loto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeRole {

	ROLE_CUSTOMER("Cliente"),
	ROLE_ADMINISTRATOR("Administrador");
	
	private String description;

}
