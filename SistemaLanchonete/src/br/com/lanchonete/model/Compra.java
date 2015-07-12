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
@Table(name = "compra")
@NamedQueries({
	@NamedQuery(name="Compra.listar",query="SELECT compra FROM Compra compra "),	
	@NamedQuery(name="Compra.buscarPorCodigo", query="SELECT compra FROM Compra compra WHERE id = :id")
})

public class Compra {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "COM_ID")
	private Long id;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "COM_HORARIO", nullable = false)
	private Date horario_compra;

	@Column(name = "COM_VALOR", scale = 2, precision = 7, nullable = false)
	private BigDecimal valor_total_compra;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COM_FUN_ID", referencedColumnName = "FUN_ID", nullable = false)
	private Funcionario funcionario;
	
	@Transient//Dizendo que é temporario
	private Integer quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getHorario_compra() {
		return horario_compra;
	}

	public void setHorario_compra(Date horario_compra) {
		this.horario_compra = horario_compra;
	}

	public BigDecimal getValor_total_compra() {
		return valor_total_compra;
	}

	public void setValor_total_compra(BigDecimal valor_total_compra) {
		this.valor_total_compra = valor_total_compra;
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

	@Override
	public String toString() {
		return "Compra [id=" + id + ", horario_compra=" + horario_compra
				+ ", valor_total_compra=" + valor_total_compra
				+ ", funcionario=" + funcionario + "]";
	}
	
	

}
