package br.com.loto.domain.repository;

import java.util.List;

import br.com.loto.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<Permission, Long>{
    
    List<Permission> findByName(String nome);
}
