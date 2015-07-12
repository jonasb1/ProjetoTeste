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
@Table(name = "itens_venda")
@NamedQueries({
	@NamedQuery(name="Itens_Venda.listar",query="SELECT itens_venda FROM Itens_Venda itens_venda "),	
	@NamedQuery(name="Itens_Venda.buscarPorCodigo", query="SELECT itens_venda FROM Itens_Venda itens_venda WHERE id = :id")
})



public class Itens_Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ITV_ID")
	private Long id;

	@Column(name = "ITV_QUANTIDADE", nullable = false)
	private Integer quantidade;

	@Column(name = "ITV_VALOR_PARCIAL_VENDA", scale = 2, precision = 7, nullable = false)
	private BigDecimal valor_parcial_venda;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITV_PRO_ID", referencedColumnName = "PRO_ID", nullable = false)
	private Produto produto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITV_VEN_ID", referencedColumnName = "VEN_ID", nullable = false)
	private Venda venda;

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

	public BigDecimal getValor_parcial_venda() {
		return valor_parcial_venda;
	}

	public void setValor_parcial_venda(BigDecimal valor_parcial_venda) {
		this.valor_parcial_venda = valor_parcial_venda;
	}



	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Override
	public String toString() {
		return "Itens_Venda [id=" + id + ", quantidade=" + quantidade
				+ ", valor_parcial_venda=" + valor_parcial_venda + ", produto="
				+ produto + ", venda=" + venda + "]";
	}

	
	
	
}
