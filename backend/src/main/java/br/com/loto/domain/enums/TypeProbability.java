package br.com.loto.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * @date 19/09/2024
 */

@Getter
@AllArgsConstructor
public enum TypeProbability {

    GOOD("Boa"), VERY_GOOD("Muito Boa"), HIGH("Alta"), VERY_HIGH("Muito Alta");

    private String description;
}
