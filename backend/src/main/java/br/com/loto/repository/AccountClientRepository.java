package br.com.loto.repository;

import br.com.loto.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountClientRepository extends JpaRepository<Account, Long>{
    
}
