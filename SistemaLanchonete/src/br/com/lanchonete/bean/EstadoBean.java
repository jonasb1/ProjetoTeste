package br.com.lanchonete.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.lanchonete.dao.EstadoDAO;
import br.com.lanchonete.model.Estado;
import br.com.lanchonete.util.FacesUtil;

@ManagedBean
@ViewScoped
public class EstadoBean {
	private Estado estadoCadastro;
	private List<Estado> listaEstados;
	private List<Estado> ListaEstadosFiltrados;
	private String acao;
	private Long id;
	
	
	public Estado getEstadoCadastro() {

		return estadoCadastro;
	}

	public void setEstadoCadastro(Estado estadoCadastro) {
		this.estadoCadastro = estadoCadastro;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public List<Estado> getListaEstadosFiltrados() {
		return ListaEstadosFiltrados;
	}

	public void setListaEstadosFiltrados(List<Estado> listaEstadosFiltrados) {
		ListaEstadosFiltrados = listaEstadosFiltrados;
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
			EstadoDAO dao = new EstadoDAO();
			dao.salvar(estadoCadastro);

			estadoCadastro = new Estado();

			FacesUtil.adicionarMsgInfo("Estado cadastrado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao incluir o estado: "
					+ ex.getMessage());
		}
	}

	public void novo() {
		estadoCadastro = new Estado();

	}

	public void carregar() {
		try {
			EstadoDAO dao = new EstadoDAO();
			listaEstados = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os estados: "
					+ ex.getMessage());

		}
	}

	public void carregarCadastro() {
		try {
			// Nao preciso maisacao=FacesUtil.getParam("estacao");
			//Capturo a ação e envio pro growl
			//FacesUtil.adicionarMsgInfo(acao);
			//String valor = FacesUtil.getParam("estId");

			if (id != null) {
				EstadoDAO dao = new EstadoDAO();
				estadoCadastro = dao.buscarPorCodigo(id);
			} else {
				estadoCadastro = new Estado();
			}
		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados do estado: "
							+ ex.getMessage());

		}

	}

	public void excluir() {

		try {
			EstadoDAO dao = new EstadoDAO();
			dao.excluir(estadoCadastro);

			FacesUtil.adicionarMsgInfo("Estado removido com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao remover o estado: "
					+ ex.getMessage());
		}
	}

	public void editar() {

		try {
			EstadoDAO dao = new EstadoDAO();
			dao.editar(estadoCadastro);

			FacesUtil.adicionarMsgInfo("Estado editado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o estado: "
					+ ex.getMessage());
		}
	}

}
