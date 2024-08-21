package br.com.loto.enums;

public enum TypeContact {

	EMAIL("E-mail"),
	MOBILE_PHONE("Telefone Movel");

	private String descricao;

	TypeContact(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
