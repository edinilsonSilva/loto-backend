package br.com.loto.api.dto.account.requests;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChangePasswordPublicRequest {

    private String newPassword;

    private String currentPassword;

    private String token;
}
