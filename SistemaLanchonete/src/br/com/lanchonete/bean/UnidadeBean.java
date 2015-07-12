package br.com.lanchonete.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.lanchonete.dao.UnidadeDAO;
import br.com.lanchonete.model.Unidade;
import br.com.lanchonete.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UnidadeBean {
	private Unidade unidadeCadastro;
	private List<Unidade> listaUnidades;
	private List<Unidade> ListaUnidadesFiltradas;

	private String acao;
	private Long id;
	
	
	public Unidade getUnidadeCadastro() {
		return unidadeCadastro;
	}

	public void setUnidadeCadastro(Unidade unidadeCadastro) {
		this.unidadeCadastro = unidadeCadastro;
	}

	public List<Unidade> getListaUnidades() {
		return listaUnidades;
	}

	public void setListaUnidades(List<Unidade> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}

	public List<Unidade> getListaUnidadesFiltradas() {
		return ListaUnidadesFiltradas;
	}

	public void setListaUnidadesFiltradas(List<Unidade> listaUnidadesFiltradas) {
		ListaUnidadesFiltradas = listaUnidadesFiltradas;
	}
	
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	

	public void salvar() {

		try {
			UnidadeDAO dao = new UnidadeDAO();
			dao.salvar(unidadeCadastro);

			unidadeCadastro = new Unidade();

			FacesUtil.adicionarMsgInfo("Unidade cadastrada com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao incluir a Unidade: "
					+ ex.getMessage());
		}
	}

	public void novo() {
		unidadeCadastro = new Unidade();

	}

	public void carregar() {
		try {
			UnidadeDAO dao = new UnidadeDAO();
			listaUnidades = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar as unidades: "
					+ ex.getMessage());

		}
	}
	
	
	
	
	public void carregarCadastro() {
		try {
		
			if (id != null) {
				UnidadeDAO dao = new UnidadeDAO();

				unidadeCadastro = dao.buscarPorCodigo(id);
			} else {

				unidadeCadastro = new Unidade();

			}
		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados da unidade: "
							+ ex.getMessage());

		}

	}

	public void excluir() {

		try {
			UnidadeDAO dao = new UnidadeDAO();
			dao.excluir(unidadeCadastro);

			FacesUtil.adicionarMsgInfo("Unidade removida com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao remover a unidade: "
					+ ex.getMessage());
		}
	}

	public void editar() {

		try {
			UnidadeDAO dao = new UnidadeDAO();
			dao.editar(unidadeCadastro);

			FacesUtil.adicionarMsgInfo("Unidade editado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o unidade: "
					+ ex.getMessage());
		}
	}
}
