package br.com.ia.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Component
@JsonAutoDetect
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "internal"})
public class Categoria implements Serializable{

	private static final long serialVersionUID = 8812299308114414438L;
	@Id
	private long id;
	@Column(nullable=false, length=100)
	private String descricao;
	
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch= FetchType.LAZY, targetEntity=Assunto.class, optional=false)
	@JsonProperty
	@JoinColumn(name="FK_ASSUNTO_ID")
	@Index(name="IDX_CATEGORIA_01")
	private Assunto assunto;

	public Categoria() {}
	
	public Categoria(long id, String descricao, Assunto assunto) {
		this.id = id;
		this.descricao = descricao;
		this.assunto = assunto;
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
	public Assunto getAssunto() {
		return this.assunto;
	}
	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}
	
	

}
