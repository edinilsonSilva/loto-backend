package br.com.loto.api.dto.game.response;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeGameResponse {

    private String name;

    private String description;

    private String pathName; // Campo utilizado como parâmetro de consulta na api caixa

    private int minNumbers; // Quantidade mínima de números que podem ser escolhidos

    private int maxNumbers; // Quantidade máxima de números que podem ser escolhidos

    private int maxNumberValue; // Valor máximo de números

}