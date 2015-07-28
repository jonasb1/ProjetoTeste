package br.com.testeOpacidade.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.testeOpacidade.model.Modelo;
import br.com.testeOpacidade.util.HibernateUtil;

public class ModeloDAO {
	public void salvar(Modelo modelo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(modelo);
			transacao.commit();

		} catch (RuntimeException ex) {
			// vejo se a transacao é diferente de nula
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Modelo> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Modelo> modelos = null;

		try {
			Query consulta = sessao.getNamedQuery("Modelo.listar");
			modelos = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return modelos; 

	}

	
	public Modelo buscarPorCodigo(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Modelo modelo = null;

		try {
			Query consulta = sessao.getNamedQuery("Modelo.buscarPorCodigo");
			consulta.setLong("id", id);
			
			modelo=(Modelo) consulta.uniqueResult();
			

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return modelo; 

	}

	public void excluir(Modelo modelo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(modelo);
			transacao.commit();

		} catch (RuntimeException ex) {
			// vejo se a transacao é diferente de nula
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}

	}
	
	
	public void editar (Modelo modelo){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transacao = null;
		
		
		try{
			transacao=sessao.beginTransaction();
			sessao.update(modelo);
			transacao.commit();
			
		}catch(Exception e){
			if(transacao != null){
				transacao.rollback();
			}
			
			throw e;
		}finally{
			sessao.close();
		}
		
	}

}
