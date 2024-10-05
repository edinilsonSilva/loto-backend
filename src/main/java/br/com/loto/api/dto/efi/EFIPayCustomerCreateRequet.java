package br.com.loto.api.dto.efi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EFIPayCustomerCreateRequet {

    private String name;

    private String cpf;

    private String phoneNumber;

    private String email;

    private String birth;
}
