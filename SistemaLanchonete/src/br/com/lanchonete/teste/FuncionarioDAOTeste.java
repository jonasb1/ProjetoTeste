package br.com.lanchonete.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;



import br.com.lanchonete.dao.FuncionarioDAO;
import br.com.lanchonete.model.Funcionario;

public class FuncionarioDAOTeste {

	@Test
	@Ignore
	public void salvar() {
		Funcionario f1 = new Funcionario();

		f1.setNome("Luiz");
		f1.setCpf("6262655556");
		f1.setFuncao("Motorista");
		f1.setSenha("15669");
		f1.setTelefone("123456789102");

		FuncionarioDAO dao = new FuncionarioDAO();
		dao.salvar(f1);

	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.listar();

		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FuncionarioDAO dao = new FuncionarioDAO();

		Funcionario f1 = dao.buscarPorCodigo(1L);
		Funcionario f2 = dao.buscarPorCodigo(2L);

		System.out.println(f1);
		System.out.println(f2);
	}

	@Test
	@Ignore
	public void excluir(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorCodigo(3L);
		 
		dao.excluir(funcionario);
	}
	
	@Test
	@Ignore
	public void editar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f = dao.buscarPorCodigo(1L);
		
		f.setId(1L);
		f.setNome("Jonas Alexandre Benevenuto");
		f.setCpf("22222");
		f.setFuncao("Gerente");
		f.setSenha("4321");
		f.setTelefone("99653510");
		
		
		dao.editar(f);
	}
	
	@Test
	public void autenticar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario fun = dao.autenticar("413.384.198-03", "123456");
		
		System.out.println("Funcionario: "+fun);
		//Assert.assertNotNull(fun); Testar se é nulo
		
	}
	
}
