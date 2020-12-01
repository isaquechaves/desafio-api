package br.com.gft.desafio.api.repository.impl;

import java.util.List;

import br.com.gft.desafio.api.model.Produto;
import br.com.gft.desafio.api.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {
	
	public List<Produto> filtrar (ProdutoFilter produtoFilter);

}
