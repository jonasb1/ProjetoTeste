package br.com.lanchonete.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.lanchonete.model.Fornecedor;
import br.com.lanchonete.util.HibernateUtil;

public class FornecedorDAO {
	public void salvar(Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(fornecedor);
			transacao.commit();

		} catch (RuntimeException ex) {
			// vejo se a transacao � diferente de nula
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Fornecedor> fornecedores = null;

		try {
			Query consulta = sessao.getNamedQuery("Fornecedor.listar");
			fornecedores = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return fornecedores; 

	}

	
	public Fornecedor buscarPorCodigo(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Fornecedor fornecedor = null;

		try {
			Query consulta = sessao.getNamedQuery("Fornecedor.buscarPorCodigo");
			consulta.setLong("id", id);
			
			fornecedor=(Fornecedor) consulta.uniqueResult();
			

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return fornecedor; 

	}

	public void excluir(Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(fornecedor);
			transacao.commit();

		} catch (RuntimeException ex) {
			// vejo se a transacao � diferente de nula
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}

	}
	
	
	public void editar (Fornecedor fornecedor){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transacao = null;
		
		
		try{
			transacao=sessao.beginTransaction();
			sessao.update(fornecedor);
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
