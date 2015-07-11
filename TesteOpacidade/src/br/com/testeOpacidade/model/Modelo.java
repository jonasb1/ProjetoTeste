package br.com.testeOpacidade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="modelo")
public class Modelo {
	@GeneratedValue
	@Id
	@Column(name="MOD_ID")
	private Long id;
	
	@NotEmpty(message="Campo descrição obrigatório")
	@Size(min=3 , max=50 , message="Informe uma descrição de 3 a 50 carácteres")
	@Column(name="MOD_DESCRICAO", nullable=false)
	private String desrcicao;
	
	@NotNull(message="O campo ano é obrigatório")
	@Column(name="MOD_ANO", nullable=false)
	@Size(min=4 , max=5 , message="Informe um ano com 4 algarismos")	
	private int ano;
	
	@NotNull(message="O campo marca é obrigatório")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MOD_MAR_ID",nullable=false,referencedColumnName="MAR_ID")
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

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", desrcicao=" + desrcicao + ", ano=" + ano
				+ ", marca=" + marca + "]";
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

	
		
	
}
