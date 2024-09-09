package br.com.loto.api.dto.requests;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChangePasswordPubRequest {

    private String newPassword;

    private String currentPassword;

    private String token;
}
