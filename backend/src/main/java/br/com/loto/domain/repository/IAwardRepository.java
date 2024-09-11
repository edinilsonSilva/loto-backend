package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAwardRepository extends JpaRepository<Award, Long> {

}
