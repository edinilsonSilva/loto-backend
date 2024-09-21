package br.com.loto.api.dto.account.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private String name;

    private String cpf;

    private String email;

    private String phone;

    private List<String> adminPermissions;

    private List<String> lotteryPermissions;

    private List<AccountWalletResponse> wallets;
}
