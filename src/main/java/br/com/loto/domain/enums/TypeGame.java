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

    MEGA_SENA("Mega Sena", "megasena", 6, 20,60),
    DUPLA_SENA("Dupla Sena", "duplasena", 6, 15, 50),
    LOTOFACIL("Lotofácil", "lotofacil", 15, 20, 25),
    QUINA("Quina", "quina", 5, 15, 80),
    LOTOMANIA("Lotomania", "lotomania", 50, 50, 100),
    TIMEMANIA("Timemania", "timemania", 10, 10, 80),
    //MAIS_MILIONARIA("+Milionária", "maismilionaria", 6, 12, 50),
    //LOTERIA_FEDERAL("Federal", "federal", 0, 0, 0),
    DIA_DE_SORTE("Dia de Sorte", "diadesorte", 7, 15, 31);
    //SUPER_SETE("Super Sete", "supersete", 7, 21, 100);

    private String description;
    private String pathName; // Campo utilizado como parâmetro de consulta na api caixa
    private int minNumbers; // Quantidade mínima de números que podem ser escolhidos
    private int maxNumbers; // Quantidade máxima de números que podem ser escolhidos
    private int maxNumberValue; // Valor máximo de números
}
