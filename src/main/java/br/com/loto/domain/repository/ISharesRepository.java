package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Shares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ISharesRepository extends PagingAndSortingRepository<Shares, Long>, JpaSpecificationExecutor<Shares>, JpaRepository<Shares, Long> {

}
