package br.com.lanchonete.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lanchonete.dao.UnidadeDAO;
import br.com.lanchonete.model.Unidade;

public class UnidadeDAOTeste {

	@Test
	@Ignore
	public void salvar() {
		Unidade u1 = new Unidade();
		u1.setDescricao("Peca");

		UnidadeDAO dao = new UnidadeDAO();
		dao.salvar(u1);
	}

	@Test
	
	public void listar() {
		UnidadeDAO dao = new UnidadeDAO();
		List<Unidade> unidades = dao.listar();

		for (Unidade unidade : unidades) {
			System.out.println(unidade);
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		UnidadeDAO dao = new UnidadeDAO();

		Unidade u1 = dao.buscarPorCodigo(2L);

		System.out.println(u1);
	}

	@Test
	@Ignore
	public void excluir() {
		UnidadeDAO dao = new UnidadeDAO();
		Unidade unidade = dao.buscarPorCodigo(3L);

		dao.excluir(unidade);
	}

	@Test
	@Ignore
	public void editar() {

		UnidadeDAO dao = new UnidadeDAO();
		Unidade unidade = dao.buscarPorCodigo(1L);

		unidade.setDescricao("Kilo");
		dao.editar(unidade);

	}

}
