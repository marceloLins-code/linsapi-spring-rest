package com.lins.works.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.From;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins.works.domain.model.Cliente;
import com.lins.works.domain.repository.clienteRepository;


@RestController
@RequestMapping("/clientess")
public class ClienteController {
	
	@PersistenceContext
	private EntityManager manager;  // interface para consulta criação exclusão..
	
	@Autowired
	private clienteRepository clienteRepository;
	
	//Endpoints:	 
	
	@GetMapping//("/clientess") <- substitudo pelo @RequestMapping("/clientess")
	public List<Cliente> listar() {
		 return clienteRepository.findAll();
		//return clienteRepository.findByNomeContaining("P");		
	}
	
	//Endpoints
	@GetMapping("/{clienteId}") // ("/clientes/{clienteId}") <-  @RequestMapping("/clientess")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		 Optional<Cliente> cliente = clienteRepository.findById(clienteId);// optional = contem ou não
		 if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}else
		 return ResponseEntity.notFound().build(); //se vazio retorna erro 404 			
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")	// a baixo, ResponseEntity pois ira ter q manipular aresposta
	private ResponseEntity<Cliente> atualizar (@PathVariable Long clienteId, @RequestBody Cliente cliente)
	{
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();		
		}
		cliente.setId(clienteId); // pegar o mesmo Id pois na atualização não indicamos o Id
		cliente = clienteRepository.save(cliente);		
		return ResponseEntity.ok(cliente);			
	}
	
	@DeleteMapping("/{clienteId}")
	private ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();		
		}
		clienteRepository.deleteById(clienteId);
		return ResponseEntity.noContent().build();
		
	}
	
	
}
