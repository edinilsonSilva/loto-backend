package br.com.loto.utils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.json.JSONObject;
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

    @NotNull
    private EfiCredential efiCredential;

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

    @Data
    public static class EfiCredential {

        @NotBlank
        private String clientId;

        @NotBlank
        private String clientSecret;

        @NotBlank
        private String certificate;

        @NotNull
        private boolean sandbox;

        @NotNull
        private boolean debug;

        public JSONObject getOptions () {

            JSONObject options = new JSONObject();
            options.put("client_id", clientId);
            options.put("client_secret", clientSecret);
            //options.put("certificate", certificate);
            options.put("sandbox", sandbox);
            return options;
        }
    }
}
