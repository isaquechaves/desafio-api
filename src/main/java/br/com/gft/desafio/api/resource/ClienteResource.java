package br.com.gft.desafio.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.gft.desafio.api.model.Cliente;
import br.com.gft.desafio.api.repository.ClienteRepository;
import br.com.gft.desafio.api.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listarTodos(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/asc")
	public List<Cliente> listarAlpha(){
		return clienteRepository.findAll(Sort.by("nome").ascending());
	}

	@GetMapping("/desc")
	public List<Cliente> listarDesc(){
		return clienteRepository.findAll(Sort.by("nome").descending());
	}
	
	@GetMapping("/nome/{nome}")
	public List<Cliente> buscarPeloNome(@PathVariable String nome){
		return clienteRepository.findByNomeContaining(nome);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response){
		clienteRepository.save(cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPeloId(@PathVariable Long id, HttpServletResponse response){
		if(clienteRepository.findById(id).isPresent()) {
			Cliente clienteEncontrado = clienteRepository.findById(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(clienteEncontrado);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
		
		Cliente clienteSalvo = clienteService.clienteAtualizar(id, cliente);
		return ResponseEntity.ok(clienteSalvo);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}
}
