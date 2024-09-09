package br.com.loto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LotteryPermission {

	ALLOW_CREATE_BETTING("Criar bolões");
	
	private String description;

}
