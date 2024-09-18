package br.com.loto.api.dto.caixa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CxPrizeBreakdownResponse {

    private String descricaoFaixa;
    private int faixa;
    private int numeroDeGanhadores;
    private BigDecimal valorPremio;
}
