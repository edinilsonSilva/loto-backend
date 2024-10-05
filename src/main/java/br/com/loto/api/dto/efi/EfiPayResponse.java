package br.com.loto.api.dto.efi;

import br.com.loto.service.efi.EFIPaymentStatus;
import br.com.loto.service.efi.EFIPaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EfiPayResponse {

    private int total;

    private int installments;

    private int installmentValue;

    private int chargeId;

    private EFIPaymentType payment;

    private EFIPaymentStatus status;
}
