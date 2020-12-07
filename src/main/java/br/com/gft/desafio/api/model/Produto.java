package br.com.gft.desafio.api.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@ApiModelProperty(value = "Código", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(example = "Livro")
	@NotBlank
	@Size(min = 3, max = 50)
	private String nome;
	
	@ApiModelProperty(example = "1")
	@Column(name = "codigoproduto")
	private String codigoProduto;
	
	@ApiModelProperty(example = "10.5")
	@NotNull
	private BigDecimal valor;
	
	@ApiModelProperty(example = "true ou false")
	@NotNull
	private Boolean promocao;
	
	@ApiModelProperty(example = "5.50")
	@NotNull
	@Column(name = "valorpromo")
	private BigDecimal valorPromo;
	
	@ApiModelProperty(example = "Livros")
	@NotBlank
	private String categoria;
	
	@ApiModelProperty(example = "imagem.jpg")
	@NotBlank
	private String imagem;
	
	@ApiModelProperty(example = "1")
	@NotNull
	private Long quantidade;
		
	@ApiModelProperty(value = "Código do fornecedor", example = "1")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;
	
	
	@ManyToMany(mappedBy = "produtos")
	private List<Venda> vendas;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getPromocao() {
		return promocao;
	}

	public void setPromocao(Boolean promocao) {
		this.promocao = promocao;
	}

	public BigDecimal getValorPromo() {
		return valorPromo;
	}

	public void setValorPromo(BigDecimal valorPromo) {
		this.valorPromo = valorPromo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
}
