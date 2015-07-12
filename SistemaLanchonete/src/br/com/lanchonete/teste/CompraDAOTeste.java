package br.com.lanchonete.teste;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lanchonete.dao.CompraDAO;
import br.com.lanchonete.dao.FuncionarioDAO;
import br.com.lanchonete.model.Compra;
import br.com.lanchonete.model.Funcionario;



public class CompraDAOTeste {
	

	@Test
	@Ignore
	public void salvar(){
		FuncionarioDAO daoFun = new FuncionarioDAO();
		Funcionario funcionario = daoFun.buscarPorCodigo(2L);
		
		Compra compra = new Compra();
		
		compra.setFuncionario(funcionario);
		//Pegando a data e horario do sistema
		compra.setHorario_compra(new Date());
		compra.setValor_total_compra(new BigDecimal(206.50D));
	
		CompraDAO dao = new CompraDAO();
		dao.salvar(compra);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		CompraDAO dao = new CompraDAO();
		List<Compra> compras = dao.listar();
		
		System.out.println(compras);
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		CompraDAO dao = new CompraDAO();
		Compra compra = dao.buscarPorCodigo(2L);
		
		System.out.println(compra);
		
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		CompraDAO dao = new CompraDAO();
		Compra compra = dao.buscarPorCodigo(4L);
		
		dao.excluir(compra);
		
	}
	
	
	@Test
	@Ignore
	public void editar (){
		FuncionarioDAO daoF = new FuncionarioDAO();
		Funcionario fun= daoF.buscarPorCodigo(2L);
		
		CompraDAO dao = new CompraDAO();
		Compra compra = dao.buscarPorCodigo(3L);
		
		compra.setHorario_compra(new Date());
		compra.setFuncionario(fun);
		compra.setValor_total_compra(new BigDecimal(15888.80D));
	
		dao.editar(compra);
		
	}
	
	@Test
	public void data(Calendar data){
		data = Calendar.getInstance();
		
	}
	
}
