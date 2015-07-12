package br.com.lanchonete.teste;

import org.junit.Test;

import br.com.lanchonete.util.HibernateUtil;

public class GerarTabelaTeste {
	
	@Test
	public void gerar(){
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
		
	}
}
