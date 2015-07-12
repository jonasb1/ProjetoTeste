package br.com.lanchonete.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.lanchonete.model.Itens_Compra;

import br.com.lanchonete.util.HibernateUtil;

public class Itens_CompraDAO {
	public void salvar(Itens_Compra itens_compra) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(itens_compra);
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
	public List<Itens_Compra> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Itens_Compra> itens = null;

		try {
			Query consulta = sessao.getNamedQuery("Itens_Compra.listar");
			itens = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return itens;

	}

	public Itens_Compra buscarPorCodigo(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Itens_Compra item = null;

		try {
			Query consulta = sessao
					.getNamedQuery("Itens_Compra.buscarPorCodigo");
			consulta.setLong("id", id);

			item = (Itens_Compra) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return item;

	}

	public void excluir(Itens_Compra itens_compra) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(itens_compra);
			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}

		} finally {
			sessao.close();
		}

	}

	public void editar(Itens_Compra itens_compra) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(itens_compra);
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
