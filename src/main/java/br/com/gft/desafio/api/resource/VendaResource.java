package br.com.gft.desafio.api.resource;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
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

import br.com.gft.desafio.api.model.Produto;
import br.com.gft.desafio.api.model.Venda;
import br.com.gft.desafio.api.model.VendaDTO;
import br.com.gft.desafio.api.repository.ClienteRepository;
import br.com.gft.desafio.api.repository.FornecedorRepository;
import br.com.gft.desafio.api.repository.ProdutoRepository;
import br.com.gft.desafio.api.repository.VendaRepository;

@RestController
@RequestMapping("/api/vendas")
public class VendaResource {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Venda> listarTodos(){
		return vendaRepository.findAll();
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
		List<Produto> produtos = new ArrayList<Produto>();
		
		Venda venda = new Venda();
		
		venda.setCliente(clienteRepository.findById(vendaDTO.getCliente()).get());
		venda.setFornecedor(fornecedorRepository.findById(vendaDTO.getFornecedor()).get());
		
		venda.setDataCompra(vendaDTO.getDataCompra());
		
		Double valor = 0.0;
		
		for(Long id : vendaDTO.getProdutos()) {
			produtos.add(produtoRepository.findById(id).get());
			
			if( produtoRepository.findById(id).get().getPromocao() == false) {
				valor = valor + produtoRepository.findById(id).get().getValor().doubleValue();
			}else {
				valor = valor + produtoRepository.findById(id).get().getValor_promo().doubleValue();
			}
		}
		BigDecimal valorTotal = new BigDecimal(valor, MathContext.DECIMAL64);
		
		venda.setTotalCompra(valorTotal);
		
		venda.setProdutos(produtos);
		
		vendaRepository.save(venda);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(venda);
	}
	
	@DeleteMapping
	public void remover(@PathVariable Long id) {
		vendaRepository.deleteById(id);
	}
}
