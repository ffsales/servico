package br.com.jbq.entrevista.servico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity(name = "tbl_servico")
public class Servico {

	@Id
	@SequenceGenerator(name = "servico_seq", sequenceName = "servico_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servico_seq")
	private long id;
	
	@Column(nullable = false, length = 50)
	@NotNull(message = "Nome é uma informação obrigatória.")
	private String nome;
	
	@Column(length = 200)
	private String descricao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
