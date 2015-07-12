package br.com.lanchonete.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.lanchonete.dao.EstadoDAO;
import br.com.lanchonete.dao.FornecedorDAO;

import br.com.lanchonete.model.Estado;
import br.com.lanchonete.model.Fornecedor;

import br.com.lanchonete.util.FacesUtil;

@ManagedBean
// Area na memoria que ira ficar gravado , apenas destrui quando fecha o
// programa Session
// View Scooped sujerido pelo Prime que destroi a view depois de ser utilizada
@ViewScoped
public class FornecedorBean {
	private Fornecedor fornecedorCadastro;
	private List<Fornecedor> listaFornecedores;
	private List<Fornecedor> ListaFornecedoresFiltrados;
	private List<Estado> listaEstados;
	private String acao;
	private Long id;

	public Fornecedor getFornecedorCadastro() {
		if (fornecedorCadastro == null) {
			fornecedorCadastro = new Fornecedor();
		}

		return fornecedorCadastro;
	}

	public void setFornecedorCadastro(Fornecedor fornecedorCadastro) {
		this.fornecedorCadastro = fornecedorCadastro;
	}

	public List<Fornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}

	public List<Fornecedor> getListaFornecedoresFiltrados() {
		return ListaFornecedoresFiltrados;
	}

	public void setListaFornecedoresFiltrados(
			List<Fornecedor> listaFornecedoresFiltrados) {
		ListaFornecedoresFiltrados = listaFornecedoresFiltrados;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
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
			FornecedorDAO dao = new FornecedorDAO();
			dao.salvar(fornecedorCadastro);

			fornecedorCadastro = new Fornecedor();

			FacesUtil.adicionarMsgInfo("Fornecedor cadastrado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar ao incluir o fornecedores: "
							+ ex.getMessage());
		}
	}

	public void novo() {
		fornecedorCadastro = new Fornecedor();

	}

	public void carregar() {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			listaFornecedores = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar listar os fornecedores: "
							+ ex.getMessage());

		}
	}

	public void carregarCadastro() {
		try {

			if (id != null) {
				FornecedorDAO dao = new FornecedorDAO();
				fornecedorCadastro = dao.buscarPorCodigo(id);

			} else {
				fornecedorCadastro = new Fornecedor();
			}

			EstadoDAO dao = new EstadoDAO();
			listaEstados = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados do fornecedores: "
							+ ex.getMessage());

		}

	}

	public void excluir() {

		try {
			FornecedorDAO dao = new FornecedorDAO();
			dao.excluir(fornecedorCadastro);

			FacesUtil.adicionarMsgInfo("Fornecedor removido com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar ao remover o fornecedores: "
							+ ex.getMessage());
		}
	}

	public void editar() {

		try {
			FornecedorDAO dao = new FornecedorDAO();
			dao.editar(fornecedorCadastro);

			FacesUtil.adicionarMsgInfo("Fornecedor editado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o fornecedor: "
					+ ex.getMessage());
		}
	}

}
