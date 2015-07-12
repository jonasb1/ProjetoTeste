package br.com.lanchonete.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.lanchonete.filter.CompraFilter;
import br.com.lanchonete.model.Compra;
import br.com.lanchonete.util.HibernateUtil;

public class CompraDAO {
	public Long salvar(Compra compra) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long id = null;

		try {
			transacao = sessao.beginTransaction();
			id = (Long) sessao.save(compra);
			sessao.save(compra);
			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<Compra> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Compra> compras = null;

		try {
			Query consulta = sessao.getNamedQuery("Compra.listar");
			compras = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return compras;
	}

	public Compra buscarPorCodigo(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Compra compra = null;

		try {
			Query consulta = sessao.getNamedQuery("Compra.buscarPorCodigo");
			consulta.setLong("id", id);

			compra = (Compra) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return compra;
	}

	public void excluir(Compra compra) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transcao = null;

		try {
			transcao = sessao.beginTransaction();
			sessao.delete(compra);
			transcao.commit();

		} catch (RuntimeException ex) {
			if (transcao != null) {
				transcao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}

	}

	public void editar(Compra compra) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(compra);
			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}

			throw ex;
		} finally {
			sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Compra> buscar(CompraFilter filtro) {
		List<Compra> compras = null;

		// Sempre usa o mesmo bloco de memoria diferente da String
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT compra FROM Compra compra ");

		// Usario nao quer filtro por data sempre o dataFinal vai ser um dia
		// antes
		if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
			sql.append("WHERE compra.horario_compra BETWEEN :dataInicial  AND :dataFinal ");
		}

		sql.append("ORDER BY compra.horario_compra ");

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			Query consulta = sessao.createQuery(sql.toString());
			if (filtro.getDataInicial() != null
					&& filtro.getDataFinal() != null) {

				consulta.setDate("dataInicial", filtro.getDataInicial());
				consulta.setDate("dataFinal", filtro.getDataFinal());
			}
			compras = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return compras;
	}
}
