package br.com.testeOpacidade.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import br.com.testeOpacidade.model.Marca;

import br.com.testeOpacidade.util.HibernateUtil;

public class MarcaDAO {
	public void salvar(Marca marca) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(marca);
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

	public void editar(Marca marca) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(marca);
			transacao.commit();

		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
		} finally {
			sessao.close();
		}

	}

	public void excluir (Marca marca){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		
		try{
			transacao = sessao.beginTransaction();
			sessao.delete(marca);
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
	public List<Marca> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Marca> marcas = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Marca.listar");
			marcas =consulta.list();
			
		}catch (RuntimeException e){
						
		}finally{
			sessao.close();
		}
		return marcas;
				
	}
	
	public Marca buscarPorCodigo (Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Marca marca = null;
	
		try{
			Query consulta = sessao.getNamedQuery("Marca.buscarPorCodigo");
			consulta.setLong("id",id);
			
			marca = (Marca)consulta.uniqueResult();
			
		}catch(RuntimeException e){
			throw e;
		}finally{
			sessao.close();
		}
		
				
		return marca;
		
	}
	
	
}
