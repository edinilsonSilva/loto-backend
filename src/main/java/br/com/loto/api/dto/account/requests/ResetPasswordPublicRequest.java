package br.com.loto.api.dto.account.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordPublicRequest {

    @NotBlank
    private String cpf;
}
