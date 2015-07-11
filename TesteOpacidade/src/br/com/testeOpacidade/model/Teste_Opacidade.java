package br.com.testeOpacidade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teste_opacidade")
public class Teste_Opacidade {
	@Id
	@GeneratedValue
	@Column(name="TES_ID", nullable=false)
	private Long id;
	
	@Column(name="TES_PROXIMO_TESTE")
	private Date proximoTeste;
	
	@Column(name="TES_DIAS")
	private int dias;
	
	@Column(name="TES_OBSERVACOES")
	private String observacoes;
	
	
	
	
}
