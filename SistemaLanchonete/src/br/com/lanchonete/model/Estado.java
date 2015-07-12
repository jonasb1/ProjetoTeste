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
@Table(name = "estado")
@NamedQueries({ 
	//Posso colocar quantas eu quiser
	@NamedQuery(name = "Estado.listar", query = "SELECT estado FROM Estado estado"),
	@NamedQuery(name= "Estado.buscarPorCodigo",query="SELECT estado FROM Estado estado WHERE estado.id = :id")
	
})
public class Estado {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "EST_ID")
	private Long id;

	
	@NotEmpty(message="O campo nome é obrigatório")
	@Size(min=2 , max=50 , message="Informe um nome de 2 a 50 carácteres")
	@Column(name = "EST_NOME", nullable = false)
	private String nome;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", nome=" + nome + "]";
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
		Estado other = (Estado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
