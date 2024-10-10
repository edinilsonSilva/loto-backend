package br.com.loto.service.games;

import br.com.loto.api.dto.efi.EfiPayPaymentCreateRequet;
import br.com.loto.domain.entity.Order;

public interface IMyOrderService {

    Order buyAGamePoolWithCreditCard(EfiPayPaymentCreateRequet request);

    Order save(Order order);
}
