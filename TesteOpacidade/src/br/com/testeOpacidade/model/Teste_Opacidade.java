package br.com.testeOpacidade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "teste_opacidade")
@NamedQueries({ @NamedQuery(name = "Teste_Opacidade.listar", query = "SELECT teste_opacidade FROM Teste_Opacidade teste_opacidade") })
public class Teste_Opacidade {
	@Id
	@GeneratedValue
	@Column(name = "TES_ID", nullable = false)
	private Long id;

	@NotNull(message = "Campo veiculo é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TES_VEI_ID", nullable = false, referencedColumnName = "VEI_ID")
	private Veiculo veiculo;

	@Column(name = "TES_OBSERVACOES")
	private String observacoes;
	
	@DecimalMin(value="0.00",message="Informe um valor maior ou a igual a zero para o campo opacidade")
	@DecimalMax(value="9999.99", message="Informe um valor menor que 100 mil para o campo opacidade")
	@Digits(integer=5, fraction=2, message="Campo opacidade permite apenas 2 dígitos depois da vírgula")
	@NotNull(message="O campo opacidade é obrigatório")
	@Column(name="TES_OPACIDADE", nullable=false)
	private Double opacidade;
	
	public Double getOpacidade() {
		return opacidade;
	}

	
	public void setOpacidade(Double opacidade) {
		this.opacidade = opacidade;
	}

	@Column(name="TES_DATA_TESTE")
	private Date data_teste;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Date getData_teste() {
		return data_teste;
	}

	public void setData_teste(Date data_teste) {
		this.data_teste = data_teste;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}



	

	@Override
	public String toString() {
		return "Teste_Opacidade [id=" + id + ", veiculo=" + veiculo
				+ ", observacoes=" + observacoes + ", opacidade=" + opacidade
				+ ", data_teste=" + data_teste + "]";
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
		Teste_Opacidade other = (Teste_Opacidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
