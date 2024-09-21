package br.com.loto.domain.repository;

import br.com.loto.domain.entity.AccountPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountPasswordRepository extends JpaRepository<AccountPassword, Long>{

    Optional<AccountPassword> findByTokenForgetPassword (String tokenForgetPassword);

}
