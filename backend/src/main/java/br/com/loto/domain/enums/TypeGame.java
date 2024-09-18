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

    MEGA_SENA("Mega Sena", "megasena"),
    DUPLA_SENA("Dupla Sena", "duplasena"),
    LOTOFACIL("Lotofácil", "lotofacil"),
    QUINA("Quina", "quina"),
    LOTOMANIA("Lotomania", "lotomania"),
    TIMEMANIA("Timemania", "timemania"),
    LOTERIA_FEDERAL("Federal", "federal"),
    DIA_DE_SORTE("Dia de Sorte", "diadesorte"),
    SUPER_SETE("Super Sete", "supersete");

    private String description;
    private String pathName;
}
