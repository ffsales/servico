package br.com.jbq.entrevista.servico.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity(name = "TBL_CLIENTE")
public class Cliente {

	@Id
	@SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
	@JsonInclude(Include.NON_NULL)
	private long id;
	
	@Column(	name = "NOME", nullable = false, length = 50)
	@NotNull(message = "Nome é uma informação obrigatória.")
	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	@Column(name = "DATA_NASCIMENTO")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(nullable = false)
	@Enumerated
	@NotNull(message = "Sexo é uma informação obrigatória.")
	private Sexo sexo;
	
	@Column(length = 18)
	private String telefone;
	
	@Column(length = 200)
	private String endereco;
	
	@ManyToMany
	@JoinTable(
		name="tbl_cliente_servico",
		joinColumns=@JoinColumn(name="id_cliente"),
		inverseJoinColumns=@JoinColumn(name="id_servico")
	)
	//@Transient
	@JsonInclude(Include.NON_NULL)
	private List<Servico> servicos;

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

}
