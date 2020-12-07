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
	
	@GetMapping
	public List<Fornecedor> listarFuncionarios(){
		return fornecedorRepository.findAll();
	}
	
	@GetMapping("/asc")
	public List<Fornecedor> listarAlpha(){
		return fornecedorRepository.findAll(Sort.by("nome").ascending());
	}
	
	@GetMapping("/desc")
	public List<Fornecedor> listarDesc(){
		return fornecedorRepository.findAll(Sort.by("nome").descending());
	}
	
	@GetMapping("/nome/{nome}")
	public List<Fornecedor> buscarPeloNome(@PathVariable String nome){
		return fornecedorRepository.findByNomeContaining(nome);
	}
	
	@PostMapping
	public ResponseEntity<Fornecedor> criar(@Valid @RequestBody Fornecedor fornecedor, HttpServletResponse response){
				
		Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, fornecedorSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> atualizar(@PathVariable Long id, @Valid @RequestBody Fornecedor fornecedor){
		Fornecedor fornecedorSalvo = fornecedorService.fornecedorAtualizar(id, fornecedor);
		
		return ResponseEntity.ok(fornecedorSalvo);
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		if(fornecedorRepository.findById(id).isPresent()) {
			fornecedorRepository.deleteById(id);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}
}
