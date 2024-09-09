package br.com.loto.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountWalletResponse {

    private Long id;

    private BigDecimal balance;

    private String currency;

}
