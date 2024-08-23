package br.com.loto.domain.repository;

import br.com.loto.domain.entity.AccountPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountPersonalRepository extends JpaRepository<AccountPersonal, Long> {

}
