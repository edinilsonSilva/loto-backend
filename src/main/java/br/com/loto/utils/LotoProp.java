package br.com.loto.utils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author Edinilson Silva - edinilson@nestec.com.br
 * <p>
 * DATA CRIACAO: 27 de dez de 2022
 */

@Data
@Component
@Validated
@ConfigurationProperties("loto")
public class LotoProp {

    @NotNull
    private Caixa caixa;

    @NotNull
    private Auth auth;

    @Data
    public static class Caixa {

        @NotBlank
        private String apiPath;
    }

    @Data
    public static class Auth {

        @NotNull
        private int expirationInMinutes;

        @NotBlank
        private String secretKey;

        @NotBlank
        private String issuer;
    }
}
