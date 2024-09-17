package br.com.loto.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "caixaClient", url = "https://servicebus2.caixa.gov.br/portaldeloterias/api")
public interface ICaixaFeign {

    @GetMapping("/megasena")
    String getMegaSenaResults();
}
