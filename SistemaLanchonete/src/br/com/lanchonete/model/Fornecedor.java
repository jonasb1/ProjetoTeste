package br.com.lanchonete.model;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "fornecedor")
@NamedQueries({
	@NamedQuery(name="Fornecedor.listar",query="SELECT fornecedor FROM Fornecedor fornecedor"),
	@NamedQuery(name="Fornecedor.buscarPorCodigo",query="SELECT fornecedor FROM Fornecedor fornecedor WHERE id = :id")
})



public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FOR_ID")
	private Long id;
	
	@NotEmpty(message="O campo nome é obrigatório")
	@Size(min=2 , max=50 , message="Informe um nome de 3 a 50 carácteres")
	@Column(name = "FOR_NOME", nullable = false)
	private String nome;
	
	@NotEmpty(message="O campo endereço é obrigatório")
	@Size(min=2 , max=50 , message="Informe um endereço de 3 a 50 carácteres")
	@Column(name = "FOR_ENDERECO", nullable = false)
	private String endereco;
	
	@Min(value=0 , message="Informe uma número maior ou igual a 0")
	@Column(name = "FOR_NUMERO", nullable = false)
	private Integer numero;
		
	@NotNull(message="O campo estado é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FOR_EST_ID", referencedColumnName = "EST_ID", nullable = false)
	private Estado estado;
	
	@Column(name = "FOR_TELEFONE", length = 15)
	private String telefone;
	
	
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", nome=" + nome + ", endereco="
				+ endereco + ", numero=" + numero + ", estado=" + estado
				+ ", telefone=" + telefone + "]";
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
		Fornecedor other = (Fornecedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
