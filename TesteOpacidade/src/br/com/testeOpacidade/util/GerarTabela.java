package br.com.testeOpacidade.util;

import org.hibernate.Session;

public class GerarTabela {

	public static void main(String[] args) {
		
			
		Session ss = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Tabela(s) gerada com sucesso");

		ss.close();

	}
}
