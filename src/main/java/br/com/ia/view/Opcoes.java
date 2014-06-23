package br.com.ia.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Opcoes implements Serializable{

	private static final long serialVersionUID = 6951615705310700903L;
	private List<Opcao> opcoes=new ArrayList<Opcao>();

	public void adicionarOpcao(Opcao opcao){
		opcoes.add(opcao);
	}
	
	public List<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

}