package br.com.ia.bdd.domain;

import java.io.Serializable;

/**
 * Classe que representa o funcionário com as suas propriedades
 * */
public class Funcionario implements Serializable{

	private static final long serialVersionUID = 4280015280099688586L;
	private String nome;
    private long matricula;
    private Marcacoes marcacoes;
    private Horario horario;
    
    public Funcionario(String nome, long matricula, Horario horario, Marcacoes marcacoes) {
		this.nome = nome;
		this.matricula = matricula;
		this.horario = horario;
		this.marcacoes = marcacoes;
	}

	public boolean entrouNoHorarioNucleo(){
    	boolean flagEntrouHorarioNucleo = true;
    	if(marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.ENTRADA).getDatetime().isBefore(horario.getInicioHorario().getDatetime())
    	  || marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.ENTRADA).getDatetime().isAfter(horario.getEntradaLimite().getDatetime())){
    		flagEntrouHorarioNucleo = false;
    	}
    	return flagEntrouHorarioNucleo;
    }
    
    public boolean saiuNoHorarioNucleo(){
    	boolean flagSaiuHorarioNucleo = true;
    	if(marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA).getDatetime().isBefore(horario.getSaida().getDatetime())
    	  || marcacoes.getMarcacaoPorTipo(TipoDeMarcacao.SAIDA).getDatetime().isAfter(horario.getTerminoHorario().getDatetime())){
    		flagSaiuHorarioNucleo = false;
    	}
    	return flagSaiuHorarioNucleo;
    }
    
	public String getNome() {
		return nome;
	}
	public long getMatricula() {
		return matricula;
	}
	public Marcacoes getMarcacoes() {
		return marcacoes;
	}

	public Horario getHorario() {
		return horario;
	}
	
}