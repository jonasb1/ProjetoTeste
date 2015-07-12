package br.com.lanchonete.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lanchonete.dao.EstadoDAO;
import br.com.lanchonete.dao.FornecedorDAO;

import br.com.lanchonete.model.Estado;
import br.com.lanchonete.model.Fornecedor;


public class FornecedorDAOTeste {
	@Test
	@Ignore
	public void salvar (){
		EstadoDAO estadodao = new EstadoDAO();
		Estado estado = estadodao.buscarPorCodigo(2L);
		
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setEndereco("Bauru vila suja");
		
		//Para fazer um BigDecimal produrto.setPreco(new BigDecimal (23.55D)); Converetendo
		fornecedor.setNome("Maria Liberula");
		
		fornecedor.setEstado(estado);
		
		FornecedorDAO dao = new FornecedorDAO();
		dao.salvar(fornecedor);
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		FornecedorDAO dao = new FornecedorDAO();
		Fornecedor fornecedor = dao.buscarPorCodigo(2L);
		
		System.out.println(fornecedor);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		FornecedorDAO dao = new FornecedorDAO();
		List<Fornecedor> fornecedores = dao.listar();
		
		System.out.println(fornecedores);
			
	}
	
	@Test
	@Ignore
	public void excluir(){
		FornecedorDAO dao = new FornecedorDAO();
		Fornecedor fornecedor = dao.buscarPorCodigo(3L);
		 
		dao.excluir(fornecedor);
	}
	
	@Test
	
	public void editar(){
		FornecedorDAO dao = new FornecedorDAO();
		
		Fornecedor f1 = dao.buscarPorCodigo(2L);
		
		f1.setEndereco("Rua Vicente De la Serra");
		f1.setNome("Luana Piovani 23");
		
		//EstadoDAO dao1 = new EstadoDAO();
		//Estado estado = dao1.buscarPorCodigo(2L);
		
		//f1.setEstado(estado);
	
		dao.editar(f1);
	}
	
	
		
}
