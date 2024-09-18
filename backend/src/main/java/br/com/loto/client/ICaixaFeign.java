package br.com.loto.client;

import br.com.loto.api.dto.caixa.CxLotteryDrawResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "caixaClient", url = "${loto.game-url.path}")
public interface ICaixaFeign {

    @GetMapping("/megasena")
    CxLotteryDrawResponse getMegaSenaResults();

    @GetMapping("/megasena/{contest}")
    CxLotteryDrawResponse getMegaSenaResultsByContest(@PathVariable String contest);

    @GetMapping("/quina")
    CxLotteryDrawResponse getQuinaResults();

    @GetMapping("/quina/{contest}")
    CxLotteryDrawResponse getQuinaResultsByContest(@PathVariable String contest);

    @GetMapping("/duplasena")
    CxLotteryDrawResponse getDuplasenaResults();

    @GetMapping("/duplasena/{contest}")
    CxLotteryDrawResponse getDuplasenaResultsByContest(@PathVariable String contest);

    @GetMapping("/lotofacil")
    CxLotteryDrawResponse getLotofacilResults();

    @GetMapping("/lotofacil/{contest}")
    CxLotteryDrawResponse getLotofacilResultsByContest(@PathVariable String contest);

    @GetMapping("/lotomania")
    CxLotteryDrawResponse getLotomaniaResults();

    @GetMapping("/lotomania/{contest}")
    CxLotteryDrawResponse getLotomaniaResultsByContest(@PathVariable String contest);

    @GetMapping("/diadesorte")
    CxLotteryDrawResponse getDiaDeSorteResults();

    @GetMapping("/diadesorte/{contest}")
    CxLotteryDrawResponse getDiaDeSorteResultsByContest(@PathVariable String contest);

    @GetMapping("/timemania")
    CxLotteryDrawResponse getTimeManiaResults();

    @GetMapping("/timemania/{contest}")
    CxLotteryDrawResponse getTimeManiaResultsByContest(@PathVariable String contest);

    @GetMapping("/federal")
    CxLotteryDrawResponse getFederalResults();

    @GetMapping("/federal/{contest}")
    CxLotteryDrawResponse getFederalResultsByContest(@PathVariable String contest);

    @GetMapping("/loteca")
    CxLotteryDrawResponse getLotecaResults();

    @GetMapping("/loteca/{contest}")
    CxLotteryDrawResponse getLotecaResultsByContest(@PathVariable String contest);

    @GetMapping("/supersete")
    CxLotteryDrawResponse getSuperSeteResults();

    @GetMapping("/supersete/{contest}")
    CxLotteryDrawResponse getSuperSeteResultsByContest(@PathVariable String contest);
}
