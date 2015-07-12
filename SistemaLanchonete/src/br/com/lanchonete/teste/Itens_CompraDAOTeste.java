package br.com.lanchonete.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lanchonete.dao.CompraDAO;
import br.com.lanchonete.dao.Itens_CompraDAO;

import br.com.lanchonete.dao.ProdutoDAO;

import br.com.lanchonete.model.Compra;
import br.com.lanchonete.model.Itens_Compra;

import br.com.lanchonete.model.Produto;

public class Itens_CompraDAOTeste {
	@Test
	@Ignore
	public void salvar() {
		CompraDAO daoCompra = new CompraDAO();
		Compra compra = daoCompra.buscarPorCodigo(2L);

		ProdutoDAO daoProduto = new ProdutoDAO();
		Produto produto = daoProduto.buscarPorCodigo(2L);

		Itens_Compra itens = new Itens_Compra();

		itens.setProduto(produto);
		itens.setCompra(compra);
		itens.setQuantidade(3);
		itens.setValor_parcial_compra(new BigDecimal(22.60D));

		Itens_CompraDAO dao = new Itens_CompraDAO();

		dao.salvar(itens);

	}

	@Test
	@Ignore
	public void listar() {
		Itens_CompraDAO dao = new Itens_CompraDAO();
		List<Itens_Compra> itens = dao.listar();

		System.out.println(itens);
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		Itens_CompraDAO dao = new Itens_CompraDAO();
		Itens_Compra item = dao.buscarPorCodigo(4L);

		System.out.println(item);
	}

	@Test
	@Ignore
	public void excluir() {
		Itens_CompraDAO dao = new Itens_CompraDAO();
		Itens_Compra item = dao.buscarPorCodigo(4L);

		dao.excluir(item);

	}

	@Test

	public void editar() {
		ProdutoDAO daoPro = new ProdutoDAO();
		Produto produto = daoPro.buscarPorCodigo(2L);

		CompraDAO daoCom = new CompraDAO();
		Compra compra = daoCom.buscarPorCodigo(1L);

		Itens_CompraDAO dao = new Itens_CompraDAO();
		Itens_Compra item = dao.buscarPorCodigo(3L);

		item.setProduto(produto);
		item.setQuantidade(1);
		item.setValor_parcial_compra(new BigDecimal(1000.6D));
		item.setCompra(compra);

		dao.editar(item);

	}

}
