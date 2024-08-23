package br.com.loto.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.loto.domain.entity.AccountPermission;

public interface IAccountPermissionRepository extends JpaRepository<AccountPermission, Long>{
    
  
}
