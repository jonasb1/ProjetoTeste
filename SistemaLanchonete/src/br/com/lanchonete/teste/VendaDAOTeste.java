package br.com.lanchonete.teste;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lanchonete.dao.FuncionarioDAO;
import br.com.lanchonete.dao.VendaDAO;
import br.com.lanchonete.filter.VendaFilter;
import br.com.lanchonete.model.Funcionario;
import br.com.lanchonete.model.Venda;

public class VendaDAOTeste {
	
	@Test
	@Ignore
	public void salvar(){
		FuncionarioDAO daoFun = new FuncionarioDAO();
		Funcionario funcionario = daoFun.buscarPorCodigo(1L);
		
		Venda venda = new Venda();
		
		venda.setFuncionario(funcionario);
		//Pegando a data e horario do sistema
		venda.setHorario_venda(new Date());
		venda.setValor_total_venda(new BigDecimal(15.80D));
	
		VendaDAO dao = new VendaDAO();
		dao.salvar(venda);
		
	}
	
	@Test
	@Ignore
	public void listar(){
		VendaDAO dao = new VendaDAO();
		List<Venda> vendas = dao.listar();
		
		System.out.println(vendas);
		
	}
	
	@Test
	@Ignore
	public void buscarPorCodigo(){
		VendaDAO dao = new VendaDAO();
		Venda venda = dao.buscarPorCodigo(1L);
		
		System.out.println(venda);
		
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		VendaDAO dao = new VendaDAO();
		Venda venda = dao.buscarPorCodigo(3L);
		
		dao.excluir(venda);
		
	}
	
	
	@Test
	@Ignore
	public void editar (){
		FuncionarioDAO daoF = new FuncionarioDAO();
		Funcionario fun= daoF.buscarPorCodigo(2L);
		
		VendaDAO dao = new VendaDAO();
		Venda venda = dao.buscarPorCodigo(3L);
		
		venda.setHorario_venda(new Date());
		venda.setFuncionario(fun);
		venda.setValor_total_venda(new BigDecimal(15888.80D));
	
		dao.editar(venda);
		
	}
	
	@Test
	@Ignore
	public void buscar() throws ParseException{
		
		//Converter para Data
		SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		VendaFilter filtro = new VendaFilter();
		filtro.setDataInicial(formato.parse("12/03/2015"));
		filtro.setDataFinal(formato.parse("03/04/2015"));
		//Se retirar os filtros ele pesquisa todos
		
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> vendas= vendaDAO.buscar(filtro);
		
		for(Venda venda:vendas){
			System.out.println(venda.getId());
			
		}
		
		
	}
	
	
}
