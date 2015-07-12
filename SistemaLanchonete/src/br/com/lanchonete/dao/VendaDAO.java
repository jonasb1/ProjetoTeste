package br.com.lanchonete.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.lanchonete.filter.VendaFilter;
import br.com.lanchonete.model.Venda;
import br.com.lanchonete.util.HibernateUtil;

public class VendaDAO {

	public Long salvar(Venda venda) {

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		Long id = null;

		try {
			transacao = sessao.beginTransaction();
			id = (Long) sessao.save(venda);
			sessao.save(venda);
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
	public List<Venda> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Venda> vendas = null;

		try {
			Query consulta = sessao.getNamedQuery("Venda.listar");
			vendas = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return vendas;
	}

	public Venda buscarPorCodigo(Long id) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Venda venda = null;

		try {
			Query consulta = sessao.getNamedQuery("Venda.buscarPorCodigo");
			consulta.setLong("id", id);

			venda = (Venda) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return venda;
	}

	public void excluir(Venda venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transcao = null;

		try {
			transcao = sessao.beginTransaction();
			sessao.delete(venda);
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

	public void editar(Venda venda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(venda);
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
	public List<Venda> buscar(VendaFilter filtro) {
		List<Venda> vendas = null;

		// Sempre usa o mesmo bloco de memoria diferente da String
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT venda FROM Venda venda ");

		// Usario nao quer filtro por data sempre o dataFinal vai ser um dia
		// antes
		if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
			sql.append("WHERE venda.horario_venda BETWEEN :dataInicial  AND :dataFinal ");
		}

		sql.append("ORDER BY venda.horario_venda ");

		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			Query consulta = sessao.createQuery(sql.toString());
			if (filtro.getDataInicial() != null
					&& filtro.getDataFinal() != null) {

				consulta.setDate("dataInicial", filtro.getDataInicial());
				consulta.setDate("dataFinal", filtro.getDataFinal());
			}
			vendas = consulta.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}

		return vendas;
	}
}
