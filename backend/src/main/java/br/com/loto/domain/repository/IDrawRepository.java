package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Draw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDrawRepository extends JpaRepository<Draw, Long> {

}
