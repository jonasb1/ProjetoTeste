package br.com.lanchonete.model;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "venda")
@NamedQueries({
	@NamedQuery(name="Venda.listar",query="SELECT venda FROM Venda venda "),	
	@NamedQuery(name="Venda.buscarPorCodigo", query="SELECT venda FROM Venda venda WHERE id = :id")
})


public class Venda {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "VEN_ID")
	private Long id;

	//Timestamp é hora e data juntos
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "VEN_HORARIO", nullable = false)
	private Date horario_venda;

	@Column(name = "VEN_VALOR", nullable = false, scale = 2, precision = 7)
	private BigDecimal valor_total_venda;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="VEN_FUN_ID",referencedColumnName="FUN_ID",nullable=false)
	private Funcionario funcionario;
	
	@Transient//Dizendo que é temporario
	private Integer quantidade;
	
	@Transient
	private BigDecimal valorRecebido;
	@Transient
	private BigDecimal valorTroco;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getHorario_venda() {
		return horario_venda;
	}

	public void setHorario_venda(Date horario_venda) {
		this.horario_venda = horario_venda;
	}

	public BigDecimal getValor_total_venda() {
		return valor_total_venda;
	}

	public void setValor_total_venda(BigDecimal valor_total_venda) {
		this.valor_total_venda = valor_total_venda;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	


	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public BigDecimal getValorTroco() {
		return valorTroco;
	}

	public void setValorTroco(BigDecimal valorTroco) {
		this.valorTroco = valorTroco;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", horario_venda=" + horario_venda
				+ ", valor_total_venda=" + valor_total_venda + ", funcionario="
				+ funcionario + "]";
	}

	
	
	
}
