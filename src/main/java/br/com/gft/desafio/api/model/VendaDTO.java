package br.com.gft.desafio.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VendaDTO {
	
	private Long id;
	
	@NotNull
	private Fornecedor fornecedor;
	
	@NotNull
	private Cliente cliente;
	
	@NotNull
	private List<Produto> produtos;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCompra;

	public Long getId() {
		return id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}


	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	
}
