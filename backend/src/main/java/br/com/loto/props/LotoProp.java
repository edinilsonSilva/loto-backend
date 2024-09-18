package br.com.loto.props;

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
    private GameUrl gameUrl;

    @Data
    public static class GameUrl {

        @NotBlank
        private String path;
    }
}
