package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IBetRepository extends PagingAndSortingRepository<Bet, Long>, JpaSpecificationExecutor<Bet>, JpaRepository<Bet, Long> {
    
}
