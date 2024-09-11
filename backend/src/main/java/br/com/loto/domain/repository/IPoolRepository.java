package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPoolRepository extends JpaRepository<Pool, Long> {

}
