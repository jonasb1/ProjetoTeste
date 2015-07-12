package br.com.lanchonete.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "produto")
@NamedQueries({
	@NamedQuery(name="Produto.listar",query="SELECT produto FROM Produto produto "),	
	@NamedQuery(name="Produto.buscarPorCodigo", query="SELECT produto FROM Produto produto WHERE id = :id")
})

public class Produto {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "PRO_ID")
	private Long id;
	
	@NotEmpty(message="O campo descrição é obrigatório")
	@Size(min=2 , max=50 , message="Informe um nome de 3 a 50 carácteres")
	@Column(name = "PRO_DESCRICAO", length = 50, nullable = false)
	private String descricao;

	
	// Precision quantas numeros depois da virgula e scale antes da virgula Ex
	// 200,00
	@NotNull(message="O campo preço é obrigatório")//Not Null Caso o usuario deixe em branco
	@DecimalMin(value="0.00",message="Informe um valor maior ou a igual a zero para o campo preço")
	@DecimalMax(value="9999.99", message="Informe um valor menor que 100 mil para o campo preço")
	@Digits(integer=5, fraction=2, message="Campo preço permite apenas 2 dígitos depois da vírgula")
	@Column(name = "PRO_PRECO", precision = 7, scale = 2, nullable = false)
	private BigDecimal preco;

	
	@NotNull(message="O campo quantidade é obrigatório")
	@Min(value=0 , message="Informe uma quantidade maior ou igual a 0")
	@Max(value=9999 , message="Informe uma quantidade menor que 10 mil")
	@Column(name = "PRO_QUANTIDADE", nullable = false)
	private Integer quantidade;

	
	@NotNull(message="O campo fornecedor é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER) // Quando carregar os produtos meu fabricante ira vir junto
	@JoinColumn(name = "PRO_FOR_ID", referencedColumnName = "FOR_ID", nullable = false)
	private Fornecedor fornecedor;

	
	@NotNull(message="O campo unidade é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRO_UNI_ID", referencedColumnName = "UNI_ID", nullable = false)
	private Unidade unidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", preco="
				+ preco + ", quantidade=" + quantidade + ", fornecedor="
				+ fornecedor + ", unidade=" + unidade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
