package br.com.loto.api.dto.efi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EfiPayCreditCardCreateRequet {

    private String paymentToken;

    private EFIPayCustomerCreateRequet customer;

    private EFIPayBillingAddressCreateRequet billingAddress;

}
