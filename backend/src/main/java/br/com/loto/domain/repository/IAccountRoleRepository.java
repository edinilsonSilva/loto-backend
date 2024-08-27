package br.com.loto.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.loto.domain.entity.AccountRole;

public interface IAccountRoleRepository extends JpaRepository<AccountRole, Long>{
    
  
}
