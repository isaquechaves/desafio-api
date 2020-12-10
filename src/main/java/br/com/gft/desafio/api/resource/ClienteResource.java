package br.com.gft.desafio.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gft.desafio.api.event.RecursoCriadoEvent;
import br.com.gft.desafio.api.model.Cliente;
import br.com.gft.desafio.api.repository.ClienteRepository;
import br.com.gft.desafio.api.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/clientes")
@Api(tags = "Cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todos")
	@GetMapping
	public List<Cliente> listarTodos(){
		return clienteRepository.findAll();
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todos em ordem alfabética")
	@GetMapping("/asc")
	public List<Cliente> listarAlpha(){
		return clienteRepository.findAll(Sort.by("nome").ascending());
	}

	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todos em ordem alfabética decrescente")
	@GetMapping("/desc")
	public List<Cliente> listarDesc(){
		return clienteRepository.findAll(Sort.by("nome").descending());
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Busca cliente pelo nome")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Cliente> buscarPeloNome(
			@ApiParam(value = "Nome de um cliente", example = "José")
			@PathVariable String nome){
		if(!clienteRepository.findByNomeContaining(nome).isEmpty()) {
			Cliente clienteEncontrado = clienteRepository.findByNomeContaining(nome).get(0);
			return ResponseEntity.status(HttpStatus.OK).body(clienteEncontrado);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Cria um novo cliente")
	@PostMapping
	public ResponseEntity<Cliente> criar(
			@ApiParam(name = "corpo", value = "Representa um novo cliente")
			@Valid @RequestBody Cliente cliente, HttpServletResponse response){
		Cliente clienteSalvo = clienteRepository.save(cliente);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Busca um cliente pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPeloId(
			@ApiParam(value = "ID de um cliente", example = "1")
			@PathVariable Long id, HttpServletResponse response){
		if(clienteRepository.findById(id).isPresent()) {
			Cliente clienteEncontrado = clienteRepository.findById(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(clienteEncontrado);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Atualiza um cliente pelo ID")
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(
			@ApiParam(value = "ID de um cliente", example = "1")
			@PathVariable Long id,
			@ApiParam(name = "corpo", value = "Representa um cliente com novos dados")
			@Valid @RequestBody Cliente cliente){
		
		Cliente clienteSalvo = clienteService.clienteAtualizar(id, cliente);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clienteSalvo);
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Deleta um cliente pelo ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> deletar(
			@ApiParam(value = "ID de um cliente", example = "1")
			@PathVariable Long id) {
		if(clienteRepository.findById(id).isPresent()) {
			clienteRepository.deleteById(id);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
