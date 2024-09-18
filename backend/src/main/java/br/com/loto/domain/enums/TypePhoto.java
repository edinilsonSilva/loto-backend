package br.com.loto.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * @date 14/08/2024
 */

@Getter
@AllArgsConstructor
public enum TypePhoto {

	LOGO_SMALL("Logo pequena"), 
	LOGO_LARGE("Logo grande"),
	PROFILE("Imagem perfil"),
	FACIAL("Imagem facial");
	
	private String description;

}
