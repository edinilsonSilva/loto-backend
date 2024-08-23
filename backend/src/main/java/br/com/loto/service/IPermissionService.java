package br.com.loto.service;

import br.com.loto.api.dto.requests.CreatePermissionRequest;
import br.com.loto.domain.entity.Permission;
import br.com.loto.exceptions.CustomResponse;

public interface IPermissionService {

    CustomResponse<Permission> create (CreatePermissionRequest request);
}
