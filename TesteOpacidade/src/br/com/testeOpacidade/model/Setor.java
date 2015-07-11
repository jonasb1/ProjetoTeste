package br.com.testeOpacidade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="setor")
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
		
	
}
