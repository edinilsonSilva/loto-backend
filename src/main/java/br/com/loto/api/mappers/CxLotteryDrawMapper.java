package br.com.loto.api.mappers;

import br.com.loto.api.dto.caixa.CxLotteryDrawResponse;
import br.com.loto.api.dto.caixa.CxPrizeBreakdownResponse;
import br.com.loto.domain.entity.LotteryDraw;
import br.com.loto.domain.entity.PrizeBreakdown;
import br.com.loto.domain.enums.TypeGame;
import org.springframework.stereotype.Component;

/**
 * @author Edinilson Silva - bateraed@gmail.com
 * <p>
 * DATA CRIACAO: 10/09/2024
 */

@Component
public class CxLotteryDrawMapper {

    public static LotteryDraw toLotteryDraw(CxLotteryDrawResponse cxLotteryDrawResponse) {
        if (cxLotteryDrawResponse == null) {
            return null;
        }

        LotteryDraw lotteryDraw = new LotteryDraw();
        lotteryDraw.setId(cxLotteryDrawResponse.getId());
        lotteryDraw.setAccumulated(cxLotteryDrawResponse.isAcumulado());
        lotteryDraw.setDrawDate(cxLotteryDrawResponse.getDataApuracao());
        lotteryDraw.setNextDrawDate(cxLotteryDrawResponse.getDataProximoConcurso());
        lotteryDraw.setDrawnNumbersInOrder(cxLotteryDrawResponse.getDezenasSorteadasOrdemSorteio());
        lotteryDraw.setShowDetailsByCity(cxLotteryDrawResponse.isExibirDetalhamentoPorCidade());
        lotteryDraw.setSpecialDrawIndicator(cxLotteryDrawResponse.getIndicadorConcursoEspecial());
        lotteryDraw.setDrawNumbers(cxLotteryDrawResponse.getListaDezenas());
        lotteryDraw.setSecondDrawNumbers(cxLotteryDrawResponse.getListaDezenasSegundoSorteio());
        lotteryDraw.setPrizeBreakdownList(cxLotteryDrawResponse.getListaRateioPremio().stream()
                .map(CxLotteryDrawMapper::toPrizeBreakdown)
                .toList());
        lotteryDraw.setDrawLocation(cxLotteryDrawResponse.getLocalSorteio());
        lotteryDraw.setDrawCityState(cxLotteryDrawResponse.getNomeMunicipioUFSorteio());
        //lotteryDraw.setHeartTeamName(cxLotteryDrawResponse.getNomeTimeCoracaoMesSorte());
        lotteryDraw.setNumber(cxLotteryDrawResponse.getNumero());
        lotteryDraw.setPreviousDrawNumber(cxLotteryDrawResponse.getNumeroConcursoAnterior());
        lotteryDraw.setFinalDrawNumber_0_5(cxLotteryDrawResponse.getNumeroConcursoFinal_0_5());
        lotteryDraw.setNextDrawNumber(cxLotteryDrawResponse.getNumeroConcursoProximo());
        lotteryDraw.setGameNumber(cxLotteryDrawResponse.getNumeroJogo());
        lotteryDraw.setNotes(cxLotteryDrawResponse.getObservacao());
        lotteryDraw.setContingencyPrize(cxLotteryDrawResponse.getPremiacaoContingencia() == null ? null : cxLotteryDrawResponse.getPremiacaoContingencia().toString());
        lotteryDraw.setGameType(TypeGame.valueOf(cxLotteryDrawResponse.getTipoJogo()));
        lotteryDraw.setPublicationType(cxLotteryDrawResponse.getTipoPublicacao());
        lotteryDraw.setLastDraw(cxLotteryDrawResponse.isUltimoConcurso());
        lotteryDraw.setCollectedAmount(cxLotteryDrawResponse.getValorArrecadado() == null ? null : cxLotteryDrawResponse.getValorArrecadado().toString());
        lotteryDraw.setAccumulatedAmount_0_5(cxLotteryDrawResponse.getValorAcumuladoConcurso_0_5() == null ? null : cxLotteryDrawResponse.getValorAcumuladoConcurso_0_5().toString());
        lotteryDraw.setSpecialAccumulatedAmount(cxLotteryDrawResponse.getValorAcumuladoConcursoEspecial() == null ? null : cxLotteryDrawResponse.getValorAcumuladoConcursoEspecial().toString());
        lotteryDraw.setNextAccumulatedAmount(cxLotteryDrawResponse.getValorAcumuladoProximoConcurso() == null ? null : cxLotteryDrawResponse.getValorAcumuladoProximoConcurso().toString());
        lotteryDraw.setEstimatedNextDrawAmount(cxLotteryDrawResponse.getValorEstimadoProximoConcurso() == null ? null : cxLotteryDrawResponse.getValorEstimadoProximoConcurso().toString());
        lotteryDraw.setGuaranteeReserveBalance(cxLotteryDrawResponse.getValorSaldoReservaGarantidora() == null ? null : cxLotteryDrawResponse.getValorSaldoReservaGarantidora().toString());
        lotteryDraw.setTotalPrizeAmountTierOne(cxLotteryDrawResponse.getValorTotalPremioFaixaUm() == null ? null : cxLotteryDrawResponse.getValorTotalPremioFaixaUm().toString());

        return lotteryDraw;
    }

    public static PrizeBreakdown toPrizeBreakdown(CxPrizeBreakdownResponse prizeBreakdownResponse) {
        if (prizeBreakdownResponse == null) {
            return null;
        }

        PrizeBreakdown prizeBreakdown = new PrizeBreakdown();
        prizeBreakdown.setPrizeTierDescription(prizeBreakdownResponse.getDescricaoFaixa());
        prizeBreakdown.setTier(prizeBreakdownResponse.getFaixa());
        prizeBreakdown.setNumberOfWinners(prizeBreakdownResponse.getNumeroDeGanhadores());
        prizeBreakdown.setPrizeAmount(prizeBreakdownResponse.getValorPremio() == null ? null : prizeBreakdownResponse.getValorPremio().toString());

        return prizeBreakdown;
    }
}
