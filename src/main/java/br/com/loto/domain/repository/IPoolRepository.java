package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Pool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPoolRepository extends PagingAndSortingRepository<Pool, Long>, JpaSpecificationExecutor<Pool>, JpaRepository<Pool, Long> {

}
