package br.com.loto.service.efi;

import br.com.loto.api.dto.efi.EfiPayPaymentCreateRequet;
import br.com.loto.api.dto.efi.EfiPayResponse;

public interface IEfiService {

    EfiPayResponse paymentByCreditCard(EfiPayPaymentCreateRequet request);

    EfiPayResponse refundCreditCard(EfiPayPaymentCreateRequet request);

    EfiPayResponse updateMetadata(EfiPayPaymentCreateRequet request);

    EfiPayResponse getCharges(Long chargeId);

}
