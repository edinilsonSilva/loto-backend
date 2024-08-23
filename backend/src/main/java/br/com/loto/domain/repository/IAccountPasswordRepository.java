package br.com.loto.domain.repository;

import br.com.loto.domain.entity.AccountPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountPasswordRepository extends JpaRepository<AccountPassword, Long>{

}
