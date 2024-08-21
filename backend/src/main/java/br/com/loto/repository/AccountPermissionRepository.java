package br.com.loto.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.loto.entity.AccountPermission;

public interface AccountPermissionRepository extends JpaRepository<AccountPermission, Long>{
    
  
}
