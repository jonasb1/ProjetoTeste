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

@Entity
@Table(name = "itens_compra")
@NamedQueries({
	@NamedQuery(name="Itens_Compra.listar",query="SELECT itens_compra FROM Itens_Compra itens_compra "),	
	@NamedQuery(name="Itens_Compra.buscarPorCodigo", query="SELECT itens_compra FROM Itens_Compra itens_compra WHERE id = :id")
})




public class Itens_Compra {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "ITC_ID")
	private Long id;

	@Column(name = "ITC_QUANTIDADE", nullable = false)
	private Integer quantidade;

	@Column(name = "ITC_VALOR_PARCIAL_COMPRA", precision = 7, scale = 2, nullable = false)
	private BigDecimal valor_parcial_compra;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITC_COM_ID", referencedColumnName = "COM_ID", nullable = false)
	private Compra compra;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITC_PRO_ID", referencedColumnName = "PRO_ID", nullable = false)
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor_parcial_compra() {
		return valor_parcial_compra;
	}

	public void setValor_parcial_compra(BigDecimal valor_parcial_compra) {
		this.valor_parcial_compra = valor_parcial_compra;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Itens_Compra [id=" + id + ", quantidade=" + quantidade
				+ ", valor_parcial_compra=" + valor_parcial_compra
				+ ", compra=" + compra + ", produto=" + produto + "]";
	}

}
