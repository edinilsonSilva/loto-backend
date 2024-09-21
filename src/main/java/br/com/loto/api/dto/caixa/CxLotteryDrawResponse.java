package br.com.loto.api.dto.caixa;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CxLotteryDrawResponse {

    private boolean acumulado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataApuracao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataProximoConcurso;

    private List<String> dezenasSorteadasOrdemSorteio;

    private boolean exibirDetalhamentoPorCidade;

    private Long id;

    private int indicadorConcursoEspecial;

    private List<String> listaDezenas;

    private List<String> listaDezenasSegundoSorteio;

    private List<CxPrizeBreakdownResponse> listaRateioPremio;

    private String localSorteio;

    private String nomeMunicipioUFSorteio;

    private String nomeTimeCoracaoMesSorte;

    private int numero;

    private int numeroConcursoAnterior;

    private int numeroConcursoFinal_0_5;

    private int numeroConcursoProximo;

    private int numeroJogo;

    private String observacao;

    private Double premiacaoContingencia;

    private String tipoJogo;

    private int tipoPublicacao;

    private boolean ultimoConcurso;

    private BigDecimal valorArrecadado;

    private BigDecimal valorAcumuladoConcurso_0_5;

    private BigDecimal valorAcumuladoConcursoEspecial;

    private BigDecimal valorAcumuladoProximoConcurso;

    private BigDecimal valorEstimadoProximoConcurso;

    private BigDecimal valorSaldoReservaGarantidora;

    private BigDecimal valorTotalPremioFaixaUm;
}