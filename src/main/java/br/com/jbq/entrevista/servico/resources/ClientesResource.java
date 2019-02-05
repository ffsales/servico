package br.com.jbq.entrevista.servico.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jbq.entrevista.servico.model.Cliente;
import br.com.jbq.entrevista.servico.model.Servico;
import br.com.jbq.entrevista.servico.repository.ClienteRepository;

@CrossOrigin
@RestController
@RequestMapping("/clientes")
public class ClientesResource {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping(value = "/listAll" ,method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listAll() {
		return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
	}
	
	@RequestMapping(value = "listWith/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listWith(@PathVariable("nome") String nome) {
		List<Cliente> clientes = clienteRepository.findByNomeContaining(nome);
		if (clientes == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Cliente cliente) {
		cliente = clienteRepository.save(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "update/{id}" ,method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cliente cliente, @PathVariable("id") Long id) {
		cliente.setId(id);
		clienteRepository.save(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "item/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> item(@PathVariable("id") Long id) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (!optionalCliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(optionalCliente.get());
	}
	
	@RequestMapping(value = "addServicos/{id}" ,method = RequestMethod.PUT)
	public ResponseEntity<Void> addServicos(@RequestBody List<Servico> servicos, @PathVariable("id") Long id) {
		
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (!optionalCliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente cliente = optionalCliente.get();
		cliente.getServicos().addAll(servicos);
		clienteRepository.save(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
