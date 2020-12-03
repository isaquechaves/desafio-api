package br.com.gft.desafio.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gft.desafio.api.model.Fornecedor;
import br.com.gft.desafio.api.repository.FornecedorRepository;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorResource {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@GetMapping
	public List<Fornecedor> listarFuncionarios(){
		return fornecedorRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Fornecedor> criar(@Valid @RequestBody Fornecedor fornecedor, HttpServletResponse response){
				
		fornecedorRepository.save(fornecedor);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedor);
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		fornecedorRepository.deleteById(id);
	}
}
