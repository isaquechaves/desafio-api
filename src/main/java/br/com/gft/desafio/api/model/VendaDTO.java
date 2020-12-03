package br.com.gft.desafio.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VendaDTO {
	
	private Long id;
	
	@NotNull
	private Long fornecedor;
	
	@NotNull
	private Long cliente;
	
	@NotNull
	private List<Long> produtos;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCompra;

	public Long getId() {
		return id;
	}

	public Long getFornecedor() {
		return fornecedor;
	}

	public Long getCliente() {
		return cliente;
	}

	public List<Long> getProdutos() {
		return produtos;
	}


	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFornecedor(Long fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public void setProdutos(List<Long> produtos) {
		this.produtos = produtos;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	
}
