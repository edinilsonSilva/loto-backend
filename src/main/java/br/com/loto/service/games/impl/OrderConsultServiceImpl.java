package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.OrderQuery;
import br.com.loto.api.dto.game.response.MyOrderResponse;
import br.com.loto.api.dto.game.response.OrderResponse;
import br.com.loto.api.mappers.OrderMapper;
import br.com.loto.domain.entity.Order;
import br.com.loto.domain.repository.IOrderRepository;
import br.com.loto.domain.specification.OrderSpecification;
import br.com.loto.service.account.IAccountValidateService;
import br.com.loto.service.games.IOrderConsultService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class OrderConsultServiceImpl implements IOrderConsultService {

    private final IOrderRepository orderRepository;
    private final IAccountValidateService accountValidateService;

    private final OrderMapper orderMapper;

    @Override
    public Page<OrderResponse> findAllByParams(OrderQuery query) {

        accountValidateService.verifyIfAccountCurrenIsAdmin();

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        Page<Order> pages = orderRepository.findAll(OrderSpecification.search(query), pageRequest);
        return pages.map(orderMapper::convertEntityToResponse);
    }
}
