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
import br.com.gft.desafio.api.model.Produto;
import br.com.gft.desafio.api.repository.ProdutoRepository;
import br.com.gft.desafio.api.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/produtos")
@Api(tags = "Produto")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ProdutoService produtoService;
	
	@ApiOperation("Lista todos")
	@GetMapping
	public List<Produto> listarTodos(){
		return produtoRepository.findAll();
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todos em ordem alfabética")
	@GetMapping("/asc")
	public List<Produto> listarAlpha(){
		return produtoRepository.findAll(Sort.by("nome").ascending());
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todos em ordem alfabética decrescente")
	@GetMapping("/desc")
	public List<Produto> listarDesc(){
		return produtoRepository.findAll(Sort.by("nome").descending()); 
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Busca pelo nome do produto")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Produto> buscarPeloNome(
			@ApiParam(value = "Nome de um produto", example = "Livro")
			@PathVariable String nome){
		if(!produtoRepository.findByNomeContaining(nome).isEmpty()) {
			Produto produtoEncontrado = produtoRepository.findByNomeContaining(nome).get(0);
			return ResponseEntity.status(HttpStatus.OK).body(produtoEncontrado);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Cria um novo produto")
	@PostMapping
	public ResponseEntity<Produto> criar(
			@ApiParam(name = "corpo", value = "Representa um novo produto")
			@Valid @RequestBody Produto produto, HttpServletResponse response){
		
		Produto produtoSalvo = produtoRepository.save(produto);
		produtoService.criarProdutoCodigo(produto);
			
		publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Busca um produto pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(
			@ApiParam(value = "ID de um produto", example = "1")
			@PathVariable Long id, HttpServletResponse response){
		if(produtoRepository.findById(id).isPresent()) {
			Produto produtoEncontrado = produtoRepository.findById(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(produtoEncontrado);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Atualiza um produto pelo ID")
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(
			@ApiParam(value = "ID de um produto", example = "1")
			@PathVariable Long id,
			@ApiParam(name = "corpo", value = "Representa um produto com novos dados")
			@Valid @RequestBody Produto produto) {
		
		Produto produtoSalvo = produtoService.produtoAtualizar(id, produto);
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Deleta um produto pelo ID")
	@DeleteMapping("/{id}")
	public void deletar(
			@ApiParam(value = "ID de um produto", example = "1")
			@PathVariable Long id) {
		if(produtoRepository.findById(id).isPresent()) {
			produtoRepository.deleteById(id);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}
}
