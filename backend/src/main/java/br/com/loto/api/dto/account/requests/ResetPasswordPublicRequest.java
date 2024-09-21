package br.com.loto.api.dto.account.requests;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordPublicRequest {

    private String cpf;
}
