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
public class Idioma implements Serializable{
	
	private static final long serialVersionUID = -3816191027657518699L;
	@Id
	private long id;
	@Column(nullable=false, length=50)
	private String descricao;
	
	public Idioma() {}
	
	public Idioma(long id, String descricao) {
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
