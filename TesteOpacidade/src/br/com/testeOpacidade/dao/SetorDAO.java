package br.com.testeOpacidade.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import br.com.testeOpacidade.model.Setor;
import br.com.testeOpacidade.util.HibernateUtil;

public class SetorDAO {
	public void salvar(Setor setor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(setor);
			transacao.commit();

		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}
	}

	public void editar(Setor setor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(setor);
			transacao.commit();

		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
		} finally {
			sessao.close();
		}

	}

	public void excluir (Setor setor){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(setor);
			transacao.commit();
			
		}catch (RuntimeException e){
			if( transacao !=null){
				transacao.rollback();
			}
		}finally{
			sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Setor> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Setor> setores = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Setor.listar");
			setores =consulta.list();
			
		}catch (RuntimeException e){
						
		}finally{
			sessao.close();
		}
		return setores;
				
	}
	
	public Setor buscarPorCodigo (Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Setor setor = null;
	
		try{
			Query consulta = sessao.getNamedQuery("Setor.buscarPorCodigo");
			consulta.setLong("id",id);
			
			setor = (Setor)consulta.uniqueResult();
			
		}catch(RuntimeException e){
			throw e;
		}finally{
			sessao.close();
		}
		
				
		return setor;
		
	}
}
