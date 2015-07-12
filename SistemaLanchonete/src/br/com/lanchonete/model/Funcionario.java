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
import org.hibernate.validator.constraints.br.CPF;;

@Entity
@Table(name = "funcionario")
@NamedQueries({
	@NamedQuery (name="Funcionario.listar",query="SELECT funcionario FROM Funcionario funcionario"),
	@NamedQuery (name="Funcionario.buscarPorCodigo", query="SELECT funcionario FROM Funcionario funcionario WHERE funcionario.id = :id"),
	@NamedQuery (name="Funcionario.autenticar", query="SELECT funcionario FROM Funcionario funcionario WHERE funcionario.cpf = :cpf AND funcionario.senha = :senha")
})

public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FUN_ID")
	private Long id;
	
	@NotEmpty(message="O campo nome é obrigatório")
	@Size(min=5 , max=50 , message="Informe um nome de 5 a 50 carácteres")
	@Column(name = "FUN_NOME", length = 50, nullable = false)
	private String nome;

	@CPF(message="O CPF é inválido ")
	@Column(name = "FUN_CPF", length = 14, nullable = false, unique = true)
	private String cpf;
	
	@NotEmpty(message="O campo senha é obrigatório")
	@Size(min=3 , max=50 , message="Tamanho inválido para o campo senha (3 - 8)")
	@Column(name = "FUN_SENHA", length = 32, nullable = false)
	private String senha;
	
	@NotEmpty(message="O campo função é obrigatório")
	@Column(name = "FUN_FUNCAO", length = 50, nullable = false)
	private String funcao;

	@Column(name = "FUN_TELEFONE", length = 15)
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf
				+ ", senha=" + senha + ", funcao=" + funcao + ", telefone="
				+ telefone + "]";
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
