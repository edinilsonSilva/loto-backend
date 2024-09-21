package br.com.loto.api.dto.account.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChangePasswordPublicRequest {

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String token;
}
