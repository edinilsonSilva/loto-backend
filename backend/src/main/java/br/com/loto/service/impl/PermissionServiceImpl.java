package br.com.loto.service.impl;

import br.com.loto.api.dto.requests.CreatePermissionRequest;
import br.com.loto.domain.entity.Permission;
import br.com.loto.domain.repository.IPermissionRepository;
import br.com.loto.exceptions.CustomResponse;
import br.com.loto.service.IPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class PermissionServiceImpl implements IPermissionService {

    private final IPermissionRepository permissionRepository;

    @Override
    @Transactional
    public CustomResponse<Permission> create(CreatePermissionRequest request) {

        Permission permission = permissionRepository.saveAndFlush(Permission.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build());

        return CustomResponse.<Permission>builder()
                .status(201)
                .message("Permissão cadastrada com sucesso.")
                .data(permission)
                .build();
    }
}
