package br.com.gft.desafio.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name="tb_fornecedor")
public class Fornecedor {
	
	@ApiModelProperty(value = "Código", example = "1")
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(example = "Fornecedor LTDA")
	@NotBlank
	@Size(min = 3, max = 50)
	private String nome;
	
	@ApiModelProperty(example = "xx.xxx.xxx/xxxx-xx")
	@NotBlank
	@CNPJ
	private String cnpj;
	
	@OneToMany
	@JoinColumn(name = "fornecedor_id")
	private List<Produto> produtos;

	
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
