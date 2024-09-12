package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IContestRepository extends PagingAndSortingRepository<Contest, Long>, JpaSpecificationExecutor<Contest>, JpaRepository<Contest, Long> {

}
