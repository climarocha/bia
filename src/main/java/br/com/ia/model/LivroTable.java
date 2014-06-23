package br.com.ia.model;

import java.util.List;

public class LivroTable extends TemplateTable {

	private static final long serialVersionUID = -6415073975958068093L;
	private Livro livro;
	private List<Livro> resultadoPesquisa;
	private long numeroTotalResultados;
	
	public LivroTable() {
		super();
	}
	
	public LivroTable(String sEcho, int iColumns, int iDisplayLenght, int iSortingCols, String sSortDir_0) {
		super(sEcho, iColumns, iDisplayLenght, iSortingCols, sSortDir_0);
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public List<Livro> getResultadoPesquisa() {
		return resultadoPesquisa;
	}
	public void setResultadoPesquisa(List<Livro> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}
	public long getNumeroTotalResultados() {
		return numeroTotalResultados;
	}
	public void setNumeroTotalResultados(long numeroTotalResultados) {
		this.numeroTotalResultados = numeroTotalResultados;
	}
	
}