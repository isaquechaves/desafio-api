package br.com.gft.desafio.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gft.desafio.api.model.Venda;
import br.com.gft.desafio.api.repository.VendaRepository;

@RestController
@RequestMapping("/api/vendas")
public class VendaResource {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@GetMapping
	public List<Venda> listarTodos(){
		return vendaRepository.findAll();
	}
}
