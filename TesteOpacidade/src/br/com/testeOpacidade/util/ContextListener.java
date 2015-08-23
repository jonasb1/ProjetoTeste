package br.com.testeOpacidade.util;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.testeOpacidade.dao.VeiculoDAO;
import br.com.testeOpacidade.model.Veiculo;

public class ContextListener implements ServletContextListener{
	//Quando eu paro o tomcat
		@Override
		public void contextDestroyed(ServletContextEvent sce) {
			HibernateUtil.getSessionFactory().close();
			
			
		}

		//Quando eu inico o tomcat
		@Override
		public void contextInitialized(ServletContextEvent sce) {
			//irá demorar menos na hora de iniciar o hibernate
			//Criar a fabrica de sessao
			HibernateUtil.getSessionFactory().openSession();
			
//			VeiculoDAO dao = new VeiculoDAO();
//			List<Veiculo> listaVeiculos = dao.listar();
//			
//			Veiculo v= new Veiculo();
//			int dias=0;
//			for (int i=0;i<listaVeiculos.size();i++){
//				v=listaVeiculos.get(i);
//				
//				Date dataHoje = new Date();
//				
//				
//				System.out.println("Nº Frota: *"+v.getNumero_frota());
//								
//				dias = -1*((int) ((dataHoje.getTime() - v.getProximoTeste().getTime()) / 86400000L));
//				dias++;
//								
//				System.out.println("Data de hoje é :"+dataHoje);
//				
//				System.out.println("Data do proximo teste: "+v.getProximoTeste());
//				System.out.println("DIAS Restantes :*"+dias);
//				
//				System.out.println("Data do ultimo teste: "+v.getUltimo_teste());
//				System.out.println("---------------------------------------------------------");
//				
//				v.setDiasRestantes(dias);
//			}
			
			
			VeiculoDAO dao = new VeiculoDAO();
			List<Veiculo> listaVeiculos = dao.listar();
			Veiculo v = new Veiculo();
			EnviarEmail e = new EnviarEmail();
			
			
			
			for (int i=0;i<listaVeiculos.size();i++){
				v=listaVeiculos.get(i);
				System.out.println("Dias restantes "+ v.getNumero_frota()+" ****"+v.getDiasRestantes());
				
				if(v.getDiasRestantes()<2){
//					e.enviarEmailPorTipo(v.getNumero_frota(), v.getDiasRestantes());
				}
								
			}
			
			
//			e.enviarEmail();
//			
		}
}
