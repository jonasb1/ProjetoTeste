package br.com.testeOpacidade.model;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "modelo")
@NamedQueries({
		@NamedQuery(name = "Modelo.listar", query = "SELECT modelo FROM Modelo modelo"),
		@NamedQuery(name = "Modelo.buscarPorCodigo", query = "SELECT modelo FROM Modelo modelo WHERE id = :id") })
public class Modelo {
	@GeneratedValue
	@Id
	@Column(name = "MOD_ID")
	private Long id;

	@NotEmpty(message = "Campo descrição obrigatório")
	@Size(min = 3, max = 50, message = "Informe uma descrição de 3 a 50 carácteres")
	@Column(name = "MOD_DESCRICAO", nullable = false)
	private String desrcicao;

	@NotNull(message = "O campo ano é obrigatório")
	@Column(name = "MOD_ANO", nullable = false)
	@Min(value=1900 , message="Informe uma número maior ou igual a 1980")
	private Integer ano;

	@NotNull(message="Campo marca é obrigatório")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MOD_MAR_ID",nullable=false, referencedColumnName="MAR_ID")
	private Marca marca;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesrcicao() {
		return desrcicao;
	}

	public void setDesrcicao(String desrcicao) {
		this.desrcicao = desrcicao;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
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
		Modelo other = (Modelo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", desrcicao=" + desrcicao + ", ano=" + ano
				+ ", marca=" + marca + "]";
	}

}
