package br.com.ia.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
@JsonAutoDetect
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "internal"})
public class Assunto implements Serializable{

	private static final long serialVersionUID = -3974865745847508286L;
	@Id
	private long id;
	@Column(nullable=false, length=100)
	private String descricao;
	
	public Assunto() {}
	
	public Assunto(long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Assunto(long id) {
		this.id = id;
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