package br.com.loto.service;

import br.com.loto.entity.Permission;
import br.com.loto.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class PermissionServiceImpl {

    private final PermissionRepository permissionRepository;

    public List<Permission> buscarTodos() {
        return permissionRepository.findAll();
    }

    public Permission inserir(Permission objeto) {
        return permissionRepository.saveAndFlush(objeto);
    }

    public Permission alterar(Permission objeto) {
        return permissionRepository.saveAndFlush(objeto);
    }

    public void excluir(Long id) {
        Permission objeto = permissionRepository.findById(id).get();
        permissionRepository.delete(objeto);
    }
}
