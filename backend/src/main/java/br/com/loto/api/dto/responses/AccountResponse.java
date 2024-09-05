package br.com.loto.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private String username;

    private String name;

    private String cpf;

    private String contact;

}
