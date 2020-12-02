package br.com.gft.desafio.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tb_venda")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedores;
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id")
	private List<Produto> produto;
	
	@NotNull
	@Column(name="totalcompra")
	private BigDecimal totalCompra;
	
	@NotNull
	@Column(name="datacompra")
	private LocalDate dataCompra;

	
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public List<Produto> getProduto(){
		return produto;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fornecedor getFornecedor() {
		return fornecedores;
	}

	public void setFornecedor(Fornecedor fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(BigDecimal totalCompra) {
		this.totalCompra = totalCompra;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	
}
