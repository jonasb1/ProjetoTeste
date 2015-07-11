package br.com.testeOpacidade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="veiculo")
public class Veiculo {
	@GeneratedValue
	@Id
	@Column(name="VEI_ID", nullable=false)
	private Long id;
	
	@NotNull(message="Campo modelo é obrigatório")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="VEI_MOD_ID",nullable=false, referencedColumnName="MOD_ID")
	private Modelo modelo;
	
	@NotNull(message="Campo setor é obrigatório")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="VEI_SET_ID",nullable=false, referencedColumnName="SET_ID")
	private Setor setor;
	
	@NotNull(message="Campo Nº frota é obrigatório")
	@Column(name="VEI_FROTA", nullable=false)
	private int numero_frota;
	
	@NotEmpty(message="Campo placa é obrigatorio")
	@Column(name="VEI_PLACA")
	private String placa;
	
	@NotNull(message="O campo opacidade é obrigatório")
	@Column(name="VEI_OPACIDADE", nullable=false)
	private Double opacidade;
	
	@NotNull(message="Campo turbinado é obrigatório")
	@Column(name="VEI_TURBINADO", nullable=false)
	private char turbinado;
	
	@NotNull(message="Campo LDA é obrigatório")
	@Column(name="VEI_LDA", nullable=false)
	private char lda;
	
	@Column(name="VEI_ULTIMO_TESTE")
	private Date ultimo_teste;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public int getNumero_frota() {
		return numero_frota;
	}

	public void setNumero_frota(int numero_frota) {
		this.numero_frota = numero_frota;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Double getOpacidade() {
		return opacidade;
	}

	public void setOpacidade(Double opacidade) {
		this.opacidade = opacidade;
	}

	public char getTurbinado() {
		return turbinado;
	}

	public void setTurbinado(char turbinado) {
		this.turbinado = turbinado;
	}

	public char getLda() {
		return lda;
	}

	public void setLda(char lda) {
		this.lda = lda;
	}
	
	public Date getUltimo_teste() {
		return ultimo_teste;
	}

	public void setUltimo_teste(Date ultimo_teste) {
		this.ultimo_teste = ultimo_teste;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", modelo=" + modelo + ", setor=" + setor
				+ ", numero_frota=" + numero_frota + ", placa=" + placa
				+ ", opacidade=" + opacidade + ", turbinado=" + turbinado
				+ ", lda=" + lda + "]";
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
