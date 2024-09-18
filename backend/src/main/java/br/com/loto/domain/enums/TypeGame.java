package br.com.loto.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * @date 14/09/2024
 */

@Getter
@AllArgsConstructor
public enum TypeGame {

    MEGA_SENA("Mega Sena"),
    LOTOFACIL("Loto Fácil"),
    QUINA("Quina"),
    DUPLA_SENA("Dupla Sena");

    private String description;
}
