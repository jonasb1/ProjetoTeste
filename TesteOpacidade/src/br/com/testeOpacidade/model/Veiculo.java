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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="veiculo")
@NamedQueries({
	@NamedQuery(name = "Veiculo.listar", query = "SELECT veiculo FROM Veiculo veiculo "),
	@NamedQuery(name = "Veiculo.listarVeiculosVencendo", query = "SELECT veiculo FROM Veiculo veiculo WHERE VEI_DIAS <= 25"),
	@NamedQuery(name = "Veiculo.buscarPorCodigo", query = "SELECT veiculo FROM Veiculo veiculo WHERE id = :id") })
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
	private Integer numero_frota;
	
	@NotEmpty(message="Campo placa é obrigatorio")
	@Column(name="VEI_PLACA")
	@Size(min=8 , max=11 , message="Informe valores válidos para o campo Placa")
	private String placa;
	
	
	@DecimalMin(value="0.00",message="Informe um valor maior ou a igual a zero para o campo opacidade")
	@DecimalMax(value="9999.99", message="Informe um valor menor que 100 mil para o campo opacidade")
	@Digits(integer=5, fraction=2, message="Campo opacidade permite apenas 2 dígitos depois da vírgula")
	@NotNull(message="O campo opacidade é obrigatório")
	@Column(name="VEI_OPACIDADE", nullable=false)
	private Double opacidade;
	
	@NotEmpty(message="Campo turbinado é obrigatório")
	@Column(name="VEI_TURBINADO", nullable=false)
	private String turbinado;
	
	@NotEmpty(message="Campo LDA é obrigatório")
	@Column(name="VEI_LDA", nullable=false)
	private String lda;
	
	@Column(name="VEI_ULTIMO_TESTE")
	private Date ultimo_teste;
	
	@Column(name="VEI_PROXIMO_TESTE")
	private Date proximoTeste;
		
	@Column(name="VEI_DIAS")	
	private int diasRestantes;
	
	public Long getId() {
		return id;
	}

	public int getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes(int diasRestantes) {
		this.diasRestantes = diasRestantes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getProximoTeste() {
		return proximoTeste;
	}

	public void setProximoTeste(Date proximoTeste) {
		this.proximoTeste = proximoTeste;
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

	public Integer getNumero_frota() {
		return numero_frota;
	}

	public void setNumero_frota(Integer numero_frota) {
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

	public String getTurbinado() {
		return turbinado;
	}

	public void setTurbinado(String turbinado) {
		this.turbinado = turbinado;
	}

	public String getLda() {
		return lda;
	}

	public void setLda(String lda) {
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
				+ ", lda=" + lda + ", ultimo_teste=" + ultimo_teste
				+ ", proximoTeste=" + proximoTeste + ", diasRestantes="
				+ diasRestantes + "]";
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
