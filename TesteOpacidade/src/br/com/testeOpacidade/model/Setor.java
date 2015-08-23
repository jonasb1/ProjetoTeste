package br.com.testeOpacidade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="setor")
@NamedQueries({
	@NamedQuery (name="Setor.listar",query="SELECT setor FROM Setor setor"),
	@NamedQuery (name="Setor.buscarPorCodigo", query="SELECT setor FROM Setor setor WHERE setor.id = :id"),
})

public class Setor {
	@Id
	@GeneratedValue
	@Column(name="SET_ID")
	private Long id;
	
	@NotEmpty(message="Campo descrição obrigatório")
	@Size(min=3, max=20, message="Informe uma descricao de 3 a 20 carácteres")
	@Column(name="SET_DESCRICAO", nullable=false)
	private String descricao;

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
		Setor other = (Setor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Setor [id=" + id + ", descricao=" + descricao + "]";
	}
	
	
		
	
}
