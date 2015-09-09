package br.com.testeOpacidade.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.testeOpacidade.model.Teste_Opacidade;
import br.com.testeOpacidade.util.HibernateUtil;

public class Teste_OpacidadeDAO {
	public void salvar(Teste_Opacidade testeOpacidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(testeOpacidade);
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
	public List<Teste_Opacidade> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Teste_Opacidade> testes_opacidade = null;

		try {
			Query consulta = sessao.getNamedQuery("Teste_Opacidade.listar");
			testes_opacidade = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return testes_opacidade; 

	}

	
	public Teste_Opacidade buscarPorCodigo(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Teste_Opacidade testeOpacidade = null;

		try {
			Query consulta = sessao.getNamedQuery("Teste_Opacidade.buscarPorCodigo");
			consulta.setLong("id", id);
			
			testeOpacidade=(Teste_Opacidade) consulta.uniqueResult();
			

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return testeOpacidade; 

	}

	public void excluir(Teste_Opacidade testeOpacidade) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(testeOpacidade);
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
	
	
	public void editar (Teste_Opacidade testeOpacidade){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transacao = null;
		
		
		try{
			transacao=sessao.beginTransaction();
			sessao.update(testeOpacidade);
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
