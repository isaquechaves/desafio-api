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
	
	@GetMapping
	public List<Produto> listarTodos(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("/asc")
	public List<Produto> listarAlpha(){
		return produtoRepository.findAll(Sort.by("nome").ascending());
	}
	
	@GetMapping("/desc")
	public List<Produto> listarDesc(){
		return produtoRepository.findAll(Sort.by("nome").descending()); 
	}
	
	@GetMapping("/nome/{nome}")
	public List<Produto> buscarPeloNome(@PathVariable String nome){
		return produtoRepository.findByNomeContaining(nome);	 
	}
	
	@PostMapping
	public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto, HttpServletResponse response){
		
		Produto produtoSalvo = produtoRepository.save(produto);
		produtoService.criarProdutoCodigo(produto);
			
		publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id, HttpServletResponse response){
		if(produtoRepository.findById(id).isPresent()) {
			Produto produtoEncontrado = produtoRepository.findById(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(produtoEncontrado);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
		
		Produto produtoSalvo = produtoService.produtoAtualizar(id, produto);
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		if(produtoRepository.findById(id).isPresent()) {
			produtoRepository.deleteById(id);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}
}
