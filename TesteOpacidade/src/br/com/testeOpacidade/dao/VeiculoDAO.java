package br.com.testeOpacidade.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.testeOpacidade.model.Veiculo;
import br.com.testeOpacidade.util.HibernateUtil;

public class VeiculoDAO {
	public void salvar(Veiculo veiculo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(veiculo);
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
	public List<Veiculo> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Veiculo> veiculos = null;

		try {
			Query consulta = sessao.getNamedQuery("Veiculo.listar");
			veiculos = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return veiculos; 

	}

	
	public Veiculo buscarPorCodigo(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Veiculo veiculo = null;

		try {
			Query consulta = sessao.getNamedQuery("Veiculo.buscarPorCodigo");
			consulta.setLong("id", id);
			
			veiculo=(Veiculo) consulta.uniqueResult();
			

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return veiculo; 

	}

	public void excluir(Veiculo veiculo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(veiculo);
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
	
	
	public void editar (Veiculo veiculo){
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transacao = null;
		
		
		try{
			transacao=sessao.beginTransaction();
			sessao.update(veiculo);
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
