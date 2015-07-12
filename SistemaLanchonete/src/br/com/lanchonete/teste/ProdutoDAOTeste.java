package br.com.lanchonete.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lanchonete.dao.FornecedorDAO;
import br.com.lanchonete.dao.ProdutoDAO;
import br.com.lanchonete.dao.UnidadeDAO;
import br.com.lanchonete.model.Fornecedor;
import br.com.lanchonete.model.Produto;
import br.com.lanchonete.model.Unidade;

public class ProdutoDAOTeste {

	@Test
	@Ignore
	public void salvar() {
		FornecedorDAO daoForne = new FornecedorDAO();
		Fornecedor fornecedor = daoForne.buscarPorCodigo(2L);

		UnidadeDAO daoUni = new UnidadeDAO();
		Unidade unidade = daoUni.buscarPorCodigo(1L);

		Produto pro = new Produto();

		pro.setDescricao("Leite Moca");
		pro.setFornecedor(fornecedor);
		pro.setPreco(new BigDecimal("4"));
		pro.setQuantidade(26);
		pro.setUnidade(unidade);

		ProdutoDAO dao = new ProdutoDAO();

		dao.salvar(pro);

	}
	
	@Test
	@Ignore
	public void listar(){
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.listar();
		
		System.out.println(produtos);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.buscarPorCodigo(2L);
		
		System.out.println(produto);
	}
	
	@Test
	@Ignore
	public void excluir(){
		ProdutoDAO dao = new ProdutoDAO();
		Produto pro = dao.buscarPorCodigo(3L);
		
		dao.excluir(pro);
				
	}
	
	
	@Test
	public void editar(){
		ProdutoDAO dao = new ProdutoDAO();
		Produto pro = dao.buscarPorCodigo(6L);
		
		pro.setDescricao("Pastel de Carne");
		pro.setQuantidade(10);
		
		dao.editar(pro);
	}
	
}
