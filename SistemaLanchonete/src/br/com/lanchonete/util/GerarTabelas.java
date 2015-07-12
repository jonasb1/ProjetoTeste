package br.com.lanchonete.util;

import org.hibernate.Session;

public class GerarTabelas {
	public static void main(String[] args) {
		
		Session ss = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Gerou com sucesso as tabelas");
		ss.close();
	}
}
