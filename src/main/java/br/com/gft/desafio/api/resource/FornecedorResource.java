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
import br.com.gft.desafio.api.model.Fornecedor;
import br.com.gft.desafio.api.repository.FornecedorRepository;
import br.com.gft.desafio.api.service.FornecedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/fornecedores")
@Api(tags = "Fornecedor")
public class FornecedorResource {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@ApiOperation("Lista todos")
	@GetMapping
	public List<Fornecedor> listarFuncionarios(){
		return fornecedorRepository.findAll();
	}
	//teste nova branch
	@ApiOperation("Lista todos em ordem alfabética")
	@GetMapping("/asc")
	public List<Fornecedor> listarAlpha(){
		return fornecedorRepository.findAll(Sort.by("nome").ascending());
	}
	
	@ApiOperation("Lista todos em ordem alfabética decrescente")
	@GetMapping("/desc")
	public List<Fornecedor> listarDesc(){
		return fornecedorRepository.findAll(Sort.by("nome").descending());
	}
	
	@ApiOperation("Busca fornecedor pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> fornecedor(
			@ApiParam(value = "ID de um fornecedor", example = "1")
			@PathVariable Long id, HttpServletResponse response) {
		if(fornecedorRepository.findById(id).isPresent()) {
			Fornecedor fornecedorEncontrado= fornecedorRepository.getOne(id);
			return ResponseEntity.status(HttpStatus.OK).body(fornecedorEncontrado);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation("Busca fornecedor pelo nome")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Fornecedor> buscarPeloNome(
			@ApiParam(value = "Nome de um fornecedor", example = "Fornecedor LTDA.")
			@PathVariable String nome, HttpServletResponse response){
		if(!fornecedorRepository.findByNomeContaining(nome).isEmpty()) {
			Fornecedor fornecedorEncontrado = fornecedorRepository.findByNomeContaining(nome).get(0);
			return ResponseEntity.status(HttpStatus.OK).body(fornecedorEncontrado);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation("Cria um novo fornecedor")
	@PostMapping
	public ResponseEntity<Fornecedor> criar(
			@ApiParam(name = "corpo", value = "Representa um novo fornecedor")
			@Valid @RequestBody Fornecedor fornecedor, HttpServletResponse response){
				
		Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, fornecedorSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
	}
	
	@ApiOperation("Atualiza um fornecedor pelo ID")
	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> atualizar(
			@ApiParam(value = "ID de um fornecedor", example = "1")
			@PathVariable Long id, 
			@ApiParam(name = "corpo", value = "Representa um fornecedor com novos dados")
			@Valid @RequestBody Fornecedor fornecedor){
		Fornecedor fornecedorSalvo = fornecedorService.fornecedorAtualizar(id, fornecedor);
		
		return ResponseEntity.ok(fornecedorSalvo);
	}
	
	@ApiOperation("Deleta um fornecedor pelo ID")
	@DeleteMapping("/{id}")
	public void remover(
			@ApiParam(value = "ID de um fornecedor", example = "1")
			@PathVariable Long id) {
		if(fornecedorRepository.findById(id).isPresent()) {
			fornecedorRepository.deleteById(id);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}
}
