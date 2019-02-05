package br.com.jbq.entrevista.servico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbq.entrevista.servico.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	public List<Cliente> findByNomeContaining(String nome);

}
