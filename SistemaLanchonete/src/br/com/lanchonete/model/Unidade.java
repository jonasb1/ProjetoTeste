package br.com.lanchonete.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "unidade")
@NamedQueries({ 
	@NamedQuery(name = "Unidade.listar" , query = "SELECT unidade FROM Unidade unidade"),
	@NamedQuery (name="Unidade.buscarPorCodigo", query="SELECT unidade FROM Unidade unidade WHERE unidade.id =:id")
})


public class Unidade {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "UNI_ID")
	private Long id;
	
	
	@NotEmpty(message="O campo descrição é obrigatório")
	@Size(min=3 , max=50, message="Informe uma descrição de 3 a 50 cáracteres")	
	@Column(name = "UNI_DESCRICAO", nullable = false)
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
	public String toString() {
		return "Unidade [id=" + id + ", descricao=" + descricao + "]";
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
		Unidade other = (Unidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
