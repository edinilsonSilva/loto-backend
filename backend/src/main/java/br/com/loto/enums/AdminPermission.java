package br.com.loto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminPermission {

	ALLOW_CREATE_ACCOUNT_ADMIN("Criar contas admins"),
	ALLOW_UPDATE_ACCOUNT_ADMIN("Atualizar contas admins"),
	ALLOW_DELETE_ACCOUNT_ADMIN("Remover contas admins"),
	ALLOW_VIEW_ACCOUNT_ADMIN("Consultar contas admins");

	private String description;

}
