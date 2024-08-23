package br.com.loto.domain.repository;

import br.com.loto.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Long>{

    Optional<Account> findByUsername(String username);
}
