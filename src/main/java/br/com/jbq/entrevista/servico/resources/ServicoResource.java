package br.com.jbq.entrevista.servico.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbq.entrevista.servico.model.Servico;
import br.com.jbq.entrevista.servico.repository.ServicoRepository;

@RestController
@RequestMapping("/servicos")
public class ServicoResource {

	@Autowired
	private ServicoRepository servicoRepository;
	
	@RequestMapping(value = "/listAll" ,method = RequestMethod.GET)
	public List<Servico> listAll() {
		return servicoRepository.findAll();
	}
	
	@RequestMapping(value = "listWith/{nome}", method = RequestMethod.GET)
	public List<Servico> listWith(@PathVariable("nome") String nome) {
		return servicoRepository.findByNomeContaining(nome);
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public void add(@RequestBody Servico servico) {
		servicoRepository.save(servico);
	}
	
	@RequestMapping(value = "update/{id}" ,method = RequestMethod.PUT)
	public void update(@RequestBody Servico servico, @PathVariable("id") Long id) {
		servico.setId(id);
		servicoRepository.save(servico);
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		servicoRepository.deleteById(id);
	}
	
	@RequestMapping(value = "item/{id}", method = RequestMethod.GET)
	public Servico item(@PathVariable("id") Long id) {
		
		Optional<Servico> optionalServico = servicoRepository.findById(id);
		
		return optionalServico.get();
	}
}
