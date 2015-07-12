package br.com.lanchonete.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lanchonete.dao.EstadoDAO;
import br.com.lanchonete.model.Estado;

public class EstadoDAOTeste {
	@Test
	// Nao quero rodar mais o salvar
	@Ignore
	public void salvar() {
		Estado e1 = new Estado();
		e1.setNome("Teste Estado 1");

		Estado e2 = new Estado();
		e2.setNome("Teste Estado 2");

		EstadoDAO dao = new EstadoDAO();
		dao.salvar(e1);
		dao.salvar(e2);

	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO dao = new EstadoDAO();
		List<Estado> estados = dao.listar();

		System.out.println(estados);
		/*
		for (Estado estado : estados) {
			System.out.println(estado);
		} 
		 */
		
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		EstadoDAO dao = new EstadoDAO();
		// Uso o L porque estou usando Long no codigo
		Estado e1 = dao.buscarPorCodigo(2L);
		Estado e2 = dao.buscarPorCodigo(5L);

		System.out.println(e1);
		System.out.println(e2);

	}

	@Test
	@Ignore
	public void excluir() {
		// è usado quando eh lancado o codigo na tabela e o usuario sabe q
		// existe Iremos usar esse

		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscarPorCodigo(2L);

		dao.excluir(estado);
		
		//if (estado != null) {
		//	dao.excluir(estado);
		//}
		//
		// Estado estado = new Estado();
		// estado.setId(2L);
		//
		// EstadoDAO dao = new EstadoDAO();
		// Estado estado = dao.buscarPorCodigo(4L);
		//

	}

	@Test
	@Ignore
	public void excluirPorCodigo() {
		//EstadoDAO dao = new EstadoDAO();
		
	}
	
	@Test

	public void editar(){
		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscarPorCodigo(1L);
		estado.setNome("São Paulo");
		
		dao.editar(estado);
			
		}
	

}
