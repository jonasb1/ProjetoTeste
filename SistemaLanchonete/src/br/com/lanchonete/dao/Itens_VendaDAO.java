package br.com.lanchonete.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.lanchonete.model.Itens_Venda;
import br.com.lanchonete.util.HibernateUtil;

public class Itens_VendaDAO {

	public void salvar(Itens_Venda itens_venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(itens_venda);
			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}

		} finally {
			sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Itens_Venda> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Itens_Venda> itens = null;

		try {
			Query consulta = sessao.getNamedQuery("Itens_Venda.listar");
			itens = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return itens;

	}
	
	
	public Itens_Venda buscarPorCodigo(Long id){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Itens_Venda item =null;
		
		try{
			Query consulta= sessao.getNamedQuery("Itens_Venda.buscarPorCodigo");
			consulta.setLong("id",id);
			
			item = (Itens_Venda) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
			throw ex;
		}finally{
			sessao.close();
		}
		
		return item;
		
	}

	public void excluir(Itens_Venda itens_venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(itens_venda);
			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}

		} finally {
			sessao.close();
		}

	}

	public void editar(Itens_Venda itens_venda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(itens_venda);
			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}

		} finally {
			sessao.close();
		}
	}

}
