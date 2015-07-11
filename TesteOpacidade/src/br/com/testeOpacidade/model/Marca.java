package br.com.testeOpacidade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="marca")
public class Marca {
	@GeneratedValue
	@Id
	@Column(name="MAR_ID")
	private Long id;
	
	@NotEmpty(message="O campo Descrição é obrigatório")
	@Size(min=2 , max=50 , message="Informe uma descrição de 3 a 50 carácteres")
	@Column(name="MAR_DESCRICAO", nullable=false)
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
