package br.com.gft.desafio.api.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.desafio.api.model.Produto;
import br.com.gft.desafio.api.model.Venda;
import br.com.gft.desafio.api.model.VendaDTO;
import br.com.gft.desafio.api.repository.ProdutoRepository;
import br.com.gft.desafio.api.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ProdutoService produtoService;
		
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public Venda criar(VendaDTO vendaDTO) {
		Venda vendaSalva = new Venda();
		
		vendaSalva.setCliente(vendaDTO.getCliente());
		vendaSalva.setFornecedor(vendaDTO.getFornecedor());
		
		vendaSalva.setDataCompra(vendaDTO.getDataCompra());
				
		vendaSalva.setTotalCompra(totalVenda(vendaDTO));
		
		vendaSalva.setProdutos(produtosList(vendaDTO));
		
		return vendaRepository.save(vendaSalva);
	}
	
	public Venda atualizar(VendaDTO vendaDTO, Long id) {
		
		Venda vendaSalva = new Venda();
		if(vendaRepository.findById(id).isPresent()) {
			
			Venda vendaAntiga = vendaRepository.getOne(id);
			devolveProdutoEstoque(vendaAntiga.getProdutos());
			
			
			vendaSalva.setId(id);
			vendaSalva.setCliente(vendaDTO.getCliente());
			vendaSalva.setFornecedor(vendaDTO.getFornecedor());
			vendaSalva.setDataCompra(vendaDTO.getDataCompra());
			vendaSalva.setTotalCompra(totalVenda(vendaDTO));
			vendaSalva.setProdutos(produtosList(vendaDTO));
			
			return vendaRepository.save(vendaSalva);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}
	
	private void devolveProdutoEstoque(List<Produto> produtos) {
		for(Produto produto : produtos) {
			produto.setQuantidade(produto.getQuantidade()+1);
			produtoRepository.save(produto);
		}
	}
	
	private BigDecimal totalVenda(VendaDTO vendaDTO) {
		
		Double valor = 0.0;
		
		//Para cada produto calcular o valor da venda, se estiver em promoção pegar o valor promocional
		for(Produto produto : vendaDTO.getProdutos()) {
			if(produtoRepository.findById(produto.getId()).isPresent()) {	
				Produto produtoEncontrado = produtoRepository.findById(produto.getId()).get();
				if(produtoEncontrado.getPromocao() == false) {
					valor = valor + produtoEncontrado.getValor().doubleValue();
				}else {
					valor = valor + produtoEncontrado.getValorPromo().doubleValue();
				}
			}else {
				throw new EmptyResultDataAccessException(1);
			}
		}
		 BigDecimal valorTotal = new BigDecimal(valor, MathContext.DECIMAL64);
		
		 return valorTotal;
	}
	
	private List<Produto> produtosList(VendaDTO vendaDTO) {
		
		List<Produto> produtos = new ArrayList<Produto>();

		for(Produto produto : vendaDTO.getProdutos()) { 
			if(produtoRepository.findById(produto.getId()).isPresent()) {
				
				Produto produtoEncontrado = produtoRepository.findById(produto.getId()).get();
				//Valida se todos os produtos são do fornecedor da venda	
					if(produtoEncontrado.getFornecedor().getId() == vendaDTO.getFornecedor().getId() && produtoEncontrado.getQuantidade() > 0) {
							produtos.add(produtoEncontrado);
							produtoService.produtoAtualizarQuantidade(produtoEncontrado);
					}else {
						throw new IllegalArgumentException();
					}
					
			}else {
				throw new EmptyResultDataAccessException(1);
			}
		}
		 return produtos;
	}
	
}
