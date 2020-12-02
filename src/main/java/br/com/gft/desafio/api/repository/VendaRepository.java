package br.com.gft.desafio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.desafio.api.model.Venda;

public interface VendaRepository extends JpaRepository <Venda, Long>{

}
