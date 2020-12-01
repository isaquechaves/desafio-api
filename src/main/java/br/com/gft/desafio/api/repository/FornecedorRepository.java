package br.com.gft.desafio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.desafio.api.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

}
