package br.com.loto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypePhoto {

	LOGO_SMALL("Logo pequena"), 
	LOGO_LARGE("Logo grande"),
	PROFILE("Imagem perfil"),
	FACIAL("Imagem facial");
	
	private String description;

}
