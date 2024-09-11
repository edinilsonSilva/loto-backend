package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IGameRepository extends PagingAndSortingRepository<Game, Long>, JpaSpecificationExecutor<Game>, JpaRepository<Game, Long> {

}
