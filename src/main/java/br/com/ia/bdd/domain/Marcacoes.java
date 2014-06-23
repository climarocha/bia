package br.com.ia.bdd.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe usada para representar as marcações efetuadas por um funcionário em um dia
 * */
public class Marcacoes implements Serializable{
	
	private static final long serialVersionUID = -7138172963924280889L;
	private Set<Marcacao> marcacoes=new HashSet<>();
	
	public Marcacoes() {}
	
	public Marcacoes(Set<Marcacao> marcacoes) {
		if(marcacoes==null){
			marcacoes = new HashSet<>();
		}
		this.marcacoes = marcacoes;
	}
	
	public void adicionarMarcacao(Marcacao marcacao){
		marcacoes.add(marcacao);
	}
	
	public Marcacao getMarcacaoPorTipo(TipoDeMarcacao tipoDeMarcacao){
		for (Marcacao marcacao : marcacoes) {
			if(marcacao.getTipoDeMarcacao() == tipoDeMarcacao){
				return marcacao;
			}
		}
		return null;
	}

	public Set<Marcacao> getMarcacoes() {
		return marcacoes;
	}
	
	
}
