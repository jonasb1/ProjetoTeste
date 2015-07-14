package br.com.testeOpacidade.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.testeOpacidade.util.HibernateUtil;

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
			
		}
}
