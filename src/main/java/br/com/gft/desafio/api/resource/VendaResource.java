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
import br.com.gft.desafio.api.model.Venda;
import br.com.gft.desafio.api.model.VendaDTO;
import br.com.gft.desafio.api.repository.VendaRepository;
import br.com.gft.desafio.api.service.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/vendas")
@Api(tags = "Vendas")
public class VendaResource {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private VendaService vendaService;
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todas")
	@GetMapping
	public List<Venda> listarTodos(){
		return vendaRepository.findAll();
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todos em ordem alfabética")
	@GetMapping("/asc")
	public List<Venda> listarTodoAlpha(){
		return vendaRepository.findAll(Sort.by("id").ascending());
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todos em ordem alfabética decrescente")
	@GetMapping("/desc")
	public List<Venda> listarTodoDesc(){
		return vendaRepository.findAll(Sort.by("id").descending());
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Busca uma venda pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Venda> buscarPorId(
			@ApiParam(value = "ID de uma venda", example = "1")
			@PathVariable Long id, HttpServletResponse response){
		if(vendaRepository.findById(id).isPresent()) {
			Venda vendaEncontrada = vendaRepository.findById(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(vendaEncontrada);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Cria uma nova venda")
	@PostMapping
	public ResponseEntity<Venda> criar(
			@ApiParam(name = "corpo", value = "Representa uma nova venda")
			@Valid @RequestBody VendaDTO vendaDTO, HttpServletResponse response){
		Venda vendaSalva = vendaService.criar(vendaDTO);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, vendaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Atualiza uma venda pelo ID")
	@PutMapping("/{id}")
	public ResponseEntity<Venda> atualizar(
			@ApiParam(value = "ID de uma venda", example = "1")
			@PathVariable Long id,
			@ApiParam(name = "corpo", value = "Representa uma venda com novos dados")
			@Valid @RequestBody VendaDTO vendaDTO, HttpServletResponse response){
		Venda vendaAtualizada = vendaService.atualizar(vendaDTO, id);
		
		return ResponseEntity.status(HttpStatus.OK).body(vendaAtualizada);
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Deleta uma venda pelo ID")
	@DeleteMapping("/{id}")
	public void remover(
			@ApiParam(value = "ID de uma venda", example = "1")
			@PathVariable Long id) {
		if(vendaRepository.findById(id).isPresent()) {
			vendaRepository.deleteById(id);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}
}
