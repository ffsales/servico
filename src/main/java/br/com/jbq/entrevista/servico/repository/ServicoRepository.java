package br.com.jbq.entrevista.servico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jbq.entrevista.servico.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{

	public List<Servico> findByNomeContaining(String nome);
}
