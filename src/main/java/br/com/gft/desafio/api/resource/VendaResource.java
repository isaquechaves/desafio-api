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
	
	
	@GetMapping
	public List<Venda> listarTodos(){
		return vendaRepository.findAll();
	}
	
	@GetMapping("/asc")
	public List<Venda> listarTodoAlpha(){
		return vendaRepository.findAll(Sort.by("id").ascending());
	}
	
	@GetMapping("/desc")
	public List<Venda> listarTodoDesc(){
		return vendaRepository.findAll(Sort.by("id").descending());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Venda> buscarPorId(@PathVariable Long id, HttpServletResponse response){
		if(vendaRepository.findById(id).isPresent()) {
			Venda vendaEncontrada = vendaRepository.findById(id).get();
			return ResponseEntity.status(HttpStatus.OK).body(vendaEncontrada);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Venda> criar(@Valid @RequestBody VendaDTO vendaDTO, HttpServletResponse response){
		Venda vendaSalva = vendaService.criar(vendaDTO);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, vendaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Venda> atualizar(@PathVariable Long id, @Valid @RequestBody VendaDTO vendaDTO, HttpServletResponse response){
		Venda vendaAtualizada = vendaService.atualizar(vendaDTO, id);
		
		return ResponseEntity.status(HttpStatus.OK).body(vendaAtualizada);
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		if(vendaRepository.findById(id).isPresent()) {
			vendaRepository.deleteById(id);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}
}
