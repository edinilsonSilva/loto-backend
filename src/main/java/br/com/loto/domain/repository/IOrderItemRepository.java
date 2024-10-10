package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Order;
import br.com.loto.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IOrderItemRepository extends PagingAndSortingRepository<OrderItem, Long>, JpaSpecificationExecutor<Order>, JpaRepository<OrderItem, Long> {
    
}
