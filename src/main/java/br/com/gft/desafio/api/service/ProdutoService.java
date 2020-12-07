package br.com.gft.desafio.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gft.desafio.api.model.Produto;
import br.com.gft.desafio.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto produtoAtualizar(Long id, Produto produto) {
		Produto produtoSalvo = buscarPeloId(id);
		BeanUtils.copyProperties(produto, produtoSalvo, "id");
		
		return produtoRepository.save(produtoSalvo);
	}

	private Produto buscarPeloId(Long id) {
		if(produtoRepository.findById(id).isPresent()) {
			Produto produtoEncontrado = produtoRepository.findById(id).get();
			return produtoRepository.save(produtoEncontrado);
		}else {
			throw new EmptyResultDataAccessException(1);
		}
	}
	
	public void produtoAtualizarQuantidade(Produto produto) {
		produto.setQuantidade(produto.getQuantidade()-1);
		produtoRepository.save(produto);
	}
	
	public void criarProdutoCodigo(Produto produto) {
		String nome = produto.getNome().substring(0, 3);
		
		String codigo = produto.getId().toString().concat(nome);
		
		produto.setCodigoProduto(codigo);
		produtoRepository.save(produto);
	}
}
