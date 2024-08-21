package br.com.loto.service;

import br.com.loto.entity.Account;
import br.com.loto.entity.AccountPermission;
import br.com.loto.entity.Permission;
import br.com.loto.repository.AccountPermissionRepository;
import br.com.loto.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPermissionServiceImpl {

    private final AccountPermissionRepository accountPermissionRepository;

    private final PermissionRepository permissionRepository;

    public void vincularPessoaPermissaoCliente(Account account){
        List<Permission> listaPermission = permissionRepository.findByName("cliente");
        if(listaPermission.size()>0){
            AccountPermission permissaoPessoa = new AccountPermission();
            permissaoPessoa.setAccount(account);
            permissaoPessoa.setPermission(listaPermission.get(0));
            accountPermissionRepository.saveAndFlush(permissaoPessoa);
        }
    }
   
}
