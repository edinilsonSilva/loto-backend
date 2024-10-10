package br.com.loto.service.games.impl;

import br.com.loto.api.dto.efi.EFIPayItemCreateRequet;
import br.com.loto.api.dto.efi.EfiPayPaymentCreateRequet;
import br.com.loto.api.dto.efi.EfiPayResponse;
import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.Order;
import br.com.loto.domain.entity.OrderItem;
import br.com.loto.domain.enums.OrderStatus;
import br.com.loto.domain.repository.IOrderItemRepository;
import br.com.loto.domain.repository.IOrderRepository;
import br.com.loto.service.account.IAccountConsultService;
import br.com.loto.service.efi.IEfiService;
import br.com.loto.service.games.IMyOrderService;
import br.com.loto.service.games.IPoolConsultService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class MyOrderServiceImpl implements IMyOrderService {

    private final IOrderRepository orderRepository;
    private final IOrderItemRepository orderItemRepository;

    private final IAccountConsultService accountConsultService;
    private final IEfiService efiService;
    private final IPoolConsultService poolConsultService;

    @Override
    @Transactional
    public Order buyAGamePoolWithCreditCard(EfiPayPaymentCreateRequet request) {

        Account accountCurrent = accountConsultService.findAccountCurrent();
        EfiPayResponse efiPayResponse = efiService.paymentByCreditCard(request);

        Order order = save(Order.builder()
                .status(OrderStatus.PAID)
                .account(accountCurrent)
                .chargeId(efiPayResponse.getChargeId())
                .paymentMethod("CREDIT_CARD")
                .transactionId(null)
                .amount(null)
                .build());

        List<OrderItem> orderItems = new ArrayList<>();
        for (EFIPayItemCreateRequet item : request.getItems())
            orderItems.add(orderItemRepository.saveAndFlush(OrderItem.builder()
                    .order(order)
                    .pool(poolConsultService.findByIdWithThow(item.getPoolId()))
                    .build()));

        order.setItems(orderItems);
        return order;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        return orderRepository.saveAndFlush(order);
    }
}
