package br.com.loto.service.impl;

import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.AccountPermission;
import br.com.loto.domain.entity.Permission;
import br.com.loto.domain.repository.IAccountPermissionRepository;
import br.com.loto.domain.repository.IPermissionRepository;
import br.com.loto.service.IAccountPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class AccountPermissionServiceImpl implements IAccountPermissionService {

    private final IAccountPermissionRepository accountPermissionRepository;

    private final IPermissionRepository permissionRepository;

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
