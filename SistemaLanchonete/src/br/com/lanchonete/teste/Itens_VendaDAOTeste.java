package br.com.lanchonete.teste;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lanchonete.dao.Itens_VendaDAO;
import br.com.lanchonete.dao.ProdutoDAO;
import br.com.lanchonete.dao.VendaDAO;
import br.com.lanchonete.model.Itens_Venda;
import br.com.lanchonete.model.Produto;
import br.com.lanchonete.model.Venda;

public class Itens_VendaDAOTeste {
	@Test
	@Ignore
	public void salvar(){
		VendaDAO daoVenda = new VendaDAO();
		Venda venda = daoVenda.buscarPorCodigo(1L);
		
		ProdutoDAO daoProduto = new ProdutoDAO();
		Produto produto = daoProduto.buscarPorCodigo(2L);
	
		Itens_Venda itens = new Itens_Venda();
		
		itens.setProduto(produto);		
		itens.setVenda(venda);
		itens.setQuantidade(223);
		itens.setValor_parcial_venda(new BigDecimal(150.60D));
		
		
		Itens_VendaDAO dao = new Itens_VendaDAO();
		dao.salvar(itens);
	
	}
	
	
	@Test
	@Ignore
	public void listar(){
		Itens_VendaDAO dao = new Itens_VendaDAO();
		List<Itens_Venda> itens = dao.listar();
		
		System.out.println(itens);
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		Itens_VendaDAO dao = new Itens_VendaDAO();
		Itens_Venda item = dao.buscarPorCodigo(2L);
		
		System.out.println(item);
	}
	
	
	
	@Test
	@Ignore
	public void excluir(){
		Itens_VendaDAO dao  = new Itens_VendaDAO();
		Itens_Venda item= dao.buscarPorCodigo(3L);
		
		dao.excluir(item);
		
		
		
		
	}
	
	
	
	@Test
	@Ignore
	public void editar(){
		ProdutoDAO daoPro = new ProdutoDAO();
		Produto produto = daoPro.buscarPorCodigo(2L);
		
		VendaDAO daoVen = new VendaDAO();
		Venda venda = daoVen.buscarPorCodigo(2L);
		
		Itens_VendaDAO dao = new  Itens_VendaDAO();		
		Itens_Venda item = dao.buscarPorCodigo(1L);
		
		
		item.setProduto(produto);
		item.setQuantidade(980);
		item.setValor_parcial_venda(new BigDecimal(656.6D));
		item.setVenda(venda);
		
		
		dao.editar(item);
		
	}
	
	
}

