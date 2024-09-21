package br.com.loto.client;

import br.com.loto.api.dto.caixa.CxLotteryDrawResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "caixaClient", url = "${loto.caixa.api-path}")
public interface ICaixaFeign {

    @GetMapping("/{typeGame}")
    CxLotteryDrawResponse getResults(@PathVariable String typeGame);

    @GetMapping("/{typeGame}/{contest}")
    CxLotteryDrawResponse getResultsByContest(@PathVariable String typeGame, @PathVariable String contest);

}
