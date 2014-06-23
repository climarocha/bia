package br.com.ia.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Index;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Component
@JsonAutoDetect
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "internal"})
@JsonTypeName("livro")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Livro implements ILivro{
	
	private static final long serialVersionUID = -2513588053122553364L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator",sequenceName="SQ_LIVRO", allocationSize=1)
	private long id;
	@Column(nullable=false, length=20)
	private String cdd;
	@Column(nullable=false, length=100)
	private String titulo;
	@Column(nullable=false, length=250)
	private String descricao;
	@Column(nullable=false, length=4)
	private int anoLancamento;
	@Column(nullable=false, length=100)
	private String autor;
	@Column(nullable=false, length=100)
	private String editora;
	@Column(nullable=true, length=200)
	private String observacao;
	
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch= FetchType.LAZY, targetEntity=Idioma.class, optional=false)
	@JoinColumn(name="FK_IDIOMA_ID")
	@Index(name="IDX_LIVRO_01")
	@JsonProperty	
	private Idioma idioma;
	
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch= FetchType.LAZY, targetEntity=Categoria.class, optional=false)
	@JoinColumn(name="FK_CATEGORIA_ID")
	@Index(name="IDX_LIVRO_02")
	@JsonProperty
	private Categoria categoria;
	
	public Livro() {}
	
	public Livro(String cdd, String titulo, String descricao,
			int anoLancamento, String autor, String editora, String observacao,
			Idioma idioma, Categoria categoria) {
		this.cdd = cdd;
		this.titulo = titulo;
		this.descricao = descricao;
		this.anoLancamento = anoLancamento;
		this.autor = autor;
		this.editora = editora;
		this.observacao = observacao;
		this.idioma = idioma;
		this.categoria = categoria;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCdd() {
		return cdd;
	}
	public void setCdd(String cdd) {
		this.cdd = cdd;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Idioma getIdioma() {
		return idioma;
	}
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}