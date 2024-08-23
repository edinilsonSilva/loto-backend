package br.com.loto.domain.repository;

import br.com.loto.domain.entity.AccountConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountConfigRepository extends JpaRepository<AccountConfig, Long>{

}
