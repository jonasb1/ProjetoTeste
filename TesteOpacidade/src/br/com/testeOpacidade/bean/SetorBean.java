package br.com.testeOpacidade.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testeOpacidade.dao.SetorDAO;

import br.com.testeOpacidade.model.Setor;
import br.com.testeOpacidade.util.FacesUtil;

@ManagedBean
@ViewScoped
public class SetorBean {
	private Setor setorCadastro;
	private List<Setor> listaSetores;
	private String acao;
	private Long id;

	public Setor getSetorCadastro() {
		return setorCadastro;
	}

	public void setSetorCadastro(Setor setorCadastro) {
		this.setorCadastro = setorCadastro;
	}

	public List<Setor> getListaSetores() {
		return listaSetores;
	}

	public void setListaSetores(List<Setor> listaSetores) {
		this.listaSetores = listaSetores;
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
			SetorDAO dao = new SetorDAO();
			dao.salvar(setorCadastro);

			setorCadastro = new Setor();

			FacesUtil.adicionarMsgInfo("Setor adicionada com sucesso");

		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar incluir o Setor "
					+ e.getMessage());

		}

	}

	public void novo() {
		setorCadastro = new Setor();
	}

	public void carregar() {
		try {
			SetorDAO dao = new SetorDAO();
			listaSetores = dao.listar();

		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao listar as Setores "
					+ e.getMessage());
		}

	}

	public void carregarCadastro() {
		try {
			if (id != null) {
				SetorDAO dao = new SetorDAO();
				setorCadastro = dao.buscarPorCodigo(id);
			} else {
				setorCadastro = new Setor();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter dados do cadastro "
					+ e.getMessage());
		}
	}

	public void excluir() {
		try {
			SetorDAO dao = new SetorDAO();
			dao.excluir(setorCadastro);

			FacesUtil.adicionarMsgInfo("Setor removido com sucesso");

		} catch (RuntimeException e) {
			FacesUtil
					.adicionarMsgErro("Erro ao excluir Setor" + e.getMessage());
		}

	}

	public void editar() {
		try {
			SetorDAO dao = new SetorDAO();
			dao.editar(setorCadastro);

			FacesUtil.adicionarMsgInfo("Setor editada com sucesso");

		} catch (RuntimeException e) {
			FacesUtil
					.adicionarMsgErro("Erro ao alterar Setor" + e.getMessage());
		}

	}

}
