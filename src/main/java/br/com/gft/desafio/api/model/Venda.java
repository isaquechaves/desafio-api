package br.com.gft.desafio.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="tb_venda")
public class Venda {
	
	@ApiModelProperty(value = "Código", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(value = "Código do fornecedor", example = "1")
	@NotNull
	@OneToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	
	@ApiModelProperty(value = "Código do cliente", example = "1")
	@NotNull
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ApiModelProperty(value = "Lista de códigos dos produtos", example = "[1, 2, 3]")
	@NotNull
	@ManyToMany
	@JoinTable(name = "tb_venda_produto",
		joinColumns = @JoinColumn(name = "venda_id"),
		inverseJoinColumns = @JoinColumn(name = "produto_id"))
	@JsonManagedReference
	private List<Produto> produtos;
	
	@ApiModelProperty(example = "5000.50")
	@Column(name="totalcompra")
	private BigDecimal totalCompra;
	
	@ApiModelProperty(example = "01/01/2020")
	@NotNull
	@Column(name="datacompra")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCompra;

	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutos(){
		return produtos;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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
