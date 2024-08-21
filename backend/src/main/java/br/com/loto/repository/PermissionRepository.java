package br.com.loto.repository;

import java.util.List;

import br.com.loto.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
    
    List<Permission> findByName(String nome);
}
