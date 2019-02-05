package br.com.jbq.entrevista.servico.model;

public enum Sexo {
	
	MASCULINO(1), FEMININO(2);
	
	Integer id;
	
	Sexo(Integer id) {
		this.id = id;
	}
}
