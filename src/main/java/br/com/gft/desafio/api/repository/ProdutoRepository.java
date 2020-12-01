package br.com.gft.desafio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.desafio.api.model.Produto;
import br.com.gft.desafio.api.repository.impl.ProdutoRepositoryQuery;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQuery {

}
