package br.com.loto.api.dto.efi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EfiPayPaymentCreateRequet {

    private List<EFIPayItemCreateRequet> items;

    private EfiPayCreditCardCreateRequet creditCard;

}
