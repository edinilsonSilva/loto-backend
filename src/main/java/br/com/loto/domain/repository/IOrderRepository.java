package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IOrderRepository extends PagingAndSortingRepository<Order, Long>, JpaSpecificationExecutor<Order>, JpaRepository<Order, Long> {
    
}
