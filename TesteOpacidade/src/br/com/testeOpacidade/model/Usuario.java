package br.com.testeOpacidade.model;

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
@Table(name="usuario")
@NamedQueries({
	@NamedQuery (name="Usuario.listar",query="SELECT usuario FROM Usuario usuario"),
	@NamedQuery (name="Usuario.buscarPorCodigo", query="SELECT usuario FROM Usuario usuario WHERE usuario.id = :id"),
	@NamedQuery (name="Usuario.autenticar", query="SELECT usuario FROM Usuario usuario WHERE usuario.codigoFuncionario = :codigoFuncionario AND usuario.senha = :senha")
})
public class Usuario {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="USU_ID")
	private Long id;
	
	@NotEmpty(message="O campo nome é obrigatório")
	@Size(min=5 , max=50 , message="Informe um nome de 5 a 50 carácteres")
	@Column(name="USU_NOME")
	private String nome;
	
	@NotEmpty(message="O campo Id do Funcionário é obrigatório")
	@Column(name="USU_CODIGO_FUNCIONARIO")
	private String codigoFuncionario;
	
	@NotEmpty(message="O campo senha é obrigatório")
	@Size(min=3 , max=50 , message="Tamanho inválido para o campo senha (3 - 8)")
	@Column(name="USU_SENHA")
	private String senha;
	
	@NotEmpty(message="O campo função é obrigatório")
	@Column(name = "FUN_FUNCAO", length = 50, nullable = false)
	private String funcao;
	
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

	public String getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
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


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", codigoFuncionario="
				+ codigoFuncionario + ", senha=" + senha + ", funcao=" + funcao
				+ "]";
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
