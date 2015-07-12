package br.com.lanchonete.dao;

import java.util.List;




import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import br.com.lanchonete.model.Estado;
import br.com.lanchonete.util.HibernateUtil;

public class EstadoDAO {
	//Tratamento do Erro Throws repropragar a o erro
	public void salvar(Estado estado)  {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		// Protecao da opreacao
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(estado);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				// Desfaco minha operacao
				transacao.rollback();

			}
			//Mostra o erro
			throw ex;
		} finally {
			sessao.close();

		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> listar(){
		Session sessao=HibernateUtil.getSessionFactory().openSession();
		
		List<Estado> estados=null;
		
		try{
			Query consulta=sessao.getNamedQuery("Estado.listar");
			 estados=consulta.list();
			
		}catch(RuntimeException ex){
		//Repropagar a excessao
			throw ex;
		}finally{
			sessao.close();
		}
		
		return  estados;
	}
	
	
	public Estado buscarPorCodigo(Long id){
		Session sessao=HibernateUtil.getSessionFactory().openSession();
		Estado estado =null;
		
		try{
			Query consulta=sessao.getNamedQuery("Estado.buscarPorCodigo");
			 
			consulta.setLong("id", id);
			
			estado = (Estado) consulta.uniqueResult();
			
		}catch(RuntimeException ex){
		//Repropagar a excessao
			throw ex;
		}finally{
			sessao.close();
		}
		
		return  estado;
	}
	
	//Utilizar quando todos os dados estao preenchidos
	public void excluir(Estado estado)  {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		// Protecao da opreacao
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(estado);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				// Desfaco minha operacao
				transacao.rollback();
			}
			//Mostra o erro
			throw ex;
		} finally {
			sessao.close();

		}
		
	}

	/*
	
	 //Quando quero excluir pela chave primaria
	public void excluirPorCodigoTeste(Long id)  {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		// Protecao da opreacao
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			Estado estado = buscarPorCodigo(id);
			
			sessao.delete(estado);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				// Desfaco minha operacao
				transacao.rollback();
			}
			//Mostra o erro
			throw ex;
		} finally {
			sessao.close();

		}
	
	
	}

	 */
		
	
	public void editar(Estado estado)  {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		// Protecao da opreacao
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			
			// Estado estadoEditar = buscarPorCodigo(estado.getId());
			// estadoEditar.setNome(estado.getNome());
			// sessao.update(estadoEditar);
			//
			
			//Todos os dados devem estar preenchidos , mas vou ter que validar na tela preenchendo todos os campos
			sessao.update(estado);
			
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				// Desfaco minha operacao
				transacao.rollback();

			}
			//Mostra o erro
			throw ex;
		} finally {
			sessao.close();

		}
	}
	
	
}
