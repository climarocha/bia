package br.com.ia.bdd.domain;

import java.io.Serializable;

/**
 * Classe responsável por constroir o funcionário.
 * */
public class FuncionarioBuilder implements Serializable{

	private static final long serialVersionUID = 2134312059053074790L;
	private String nome;
    private long matricula;
    private Horario horario;
    private Marcacoes marcacoes=new Marcacoes();
    
    public FuncionarioBuilder paraFuncionario(String nome){
    	this.nome = nome;
    	return this;
    }
    
    public FuncionarioBuilder comMatricula(long matricula){
    	this.matricula=matricula;
    	return this;
    }
    
    public FuncionarioBuilder entrouNaEmpresaAs(String time){
    	this.marcacoes.adicionarMarcacao(new Marcacao(time, TipoDeMarcacao.ENTRADA));
    	return this;
    }
    
    public FuncionarioBuilder foiAlmocarAs(String time){
    	this.marcacoes.adicionarMarcacao(new Marcacao(time, TipoDeMarcacao.SAIDA_ALMOCO));
    	return this;
    }
    
    public FuncionarioBuilder retornouDoAlmocoAs(String time){
    	this.marcacoes.adicionarMarcacao(new Marcacao(time, TipoDeMarcacao.RETORNO_ALMOCO));
    	return this;
    }
    
    public FuncionarioBuilder foiEmboraAs(String time){
    	this.marcacoes.adicionarMarcacao(new Marcacao(time, TipoDeMarcacao.SAIDA));
    	return this;
    }
    
    public Funcionario constroi(){
    	return new Funcionario(nome, matricula, horario, marcacoes);
    }
    
    public FuncionarioBuilder comHorario(Horario horario){
    	this.horario = horario;
    	return this;
    }

}