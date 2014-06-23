package br.com.ia.model;

import java.io.Serializable;

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

@Entity
@Component
@JsonAutoDetect
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "internal"})
public class Funcionario implements Serializable{

	private static final long serialVersionUID = -3550816657671137224L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator",sequenceName="SQ_FUNCIONARIO", allocationSize=1)
	private long id;
	@Column(nullable = false, unique=true)
	private long matricula;
	@Column(nullable=false, length=100)
	private String nome;
	@Column(nullable=false, length=100)
	private String email;
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch= FetchType.LAZY, targetEntity=Cargo.class, optional=false)
	@JoinColumn(name="FK_CARGO_ID")
	@Index(name="IDX_FUNCIONARIO_01")
	@JsonProperty
	private Cargo cargo;
	
	public Funcionario() {}
	
	public Funcionario(long matricula, String nome, String email, Cargo cargo) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.cargo = cargo;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMatricula() {
		return matricula;
	}
	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	
}
