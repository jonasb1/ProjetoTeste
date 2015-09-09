package br.com.testeOpacidade.teste;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.testeOpacidade.dao.VeiculoDAO;
import br.com.testeOpacidade.model.Veiculo;

public class TesteDatas {
	
	@Ignore
	@Test
	public void teste(){
		
		//Calendar today = Calendar.getInstance();
		
		VeiculoDAO dao = new VeiculoDAO();
		
		Veiculo vei= dao.buscarPorCodigo(1L);
		  SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy"); 
	
	  Date teste = vei.getUltimo_teste();
		  System.out.println(teste);
		  
		
		//teste.add(Calendar.DAY_OF_MONTH,+150);
		
		
		System.out.println("IRa ser dia"+sd.format(teste.getTime()));
		
				
		Date dataDoUsuario = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(dataDoUsuario);
		c.add(Calendar.DATE, +150);
		
		dataDoUsuario = c.getTime();
		
		
		System.out.println("Data somada é "+dataDoUsuario);
		  
		  
//		  Calendar cal = new Date;
//		  cal.setTime(vei.getUltimo_teste()); // Objeto Date() do usuário
//		  cal.add(cal.DAY_OF_MONTH, +1);
//
//		  System.out.println("DATA: " + cal.getTime());
		  
		  
	}
	@Ignore
	@Test
	public void adicionarDias(){
		List<Veiculo> listaVeiculos;
		
		Date dataHoje = new java.util.Date();
		
		VeiculoDAO dao = new VeiculoDAO();
		listaVeiculos = dao.listar();
		
		int dias;
		
		Veiculo v = new Veiculo();
				
		for(int i=0; i<listaVeiculos.size();i++){
			dias = (int) ((dataHoje.getTime() - v.getUltimo_teste().getTime()) / 86400000L);
			
//			v.setDiasRestantes(dias);
			System.out.println("dasdas"+dias);
		}
				
	}
	@Test
	public void listar(){
		VeiculoDAO dao = new VeiculoDAO();
		List<Veiculo> veiculos =dao.listar();
		
		for (Veiculo veiculo : veiculos) {
			System.out.println("Numero da frota é :"+veiculo.getNumero_frota());
		}
		
	}
	
}	
