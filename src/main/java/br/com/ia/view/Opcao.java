package br.com.ia.view;

import java.io.Serializable;

public class Opcao implements Serializable{
	
	private static final long serialVersionUID = -2581265200295468L;
	private long id;
	private String descricao;
	
	public Opcao(){}
	
	public Opcao(long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
