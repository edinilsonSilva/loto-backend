package br.com.loto.api.dto.account.requests;

import br.com.loto.domain.enums.TypeContact;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountContactRequest {

    @NotNull
    private TypeContact type;

    @NotBlank
    private String value;

}
