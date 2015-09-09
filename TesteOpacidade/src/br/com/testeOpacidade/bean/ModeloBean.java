package br.com.testeOpacidade.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.testeOpacidade.dao.MarcaDAO;
import br.com.testeOpacidade.dao.ModeloDAO;
import br.com.testeOpacidade.model.Marca;
import br.com.testeOpacidade.model.Modelo;
import br.com.testeOpacidade.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ModeloBean {
	private Modelo modeloCadastro;
	private List<Modelo> listaModelos;
	private List<Modelo> ListaModelosFiltrados;
	private List<Marca> listaMarcas;
	private String acao;
	private Long id;

	public Modelo getModeloCadastro() {
		if (modeloCadastro == null) {
			modeloCadastro = new Modelo();
		}

		return modeloCadastro;
	}

	public void setModeloCadastro(Modelo modeloCadastro) {
		this.modeloCadastro = modeloCadastro;
	}

	public List<Modelo> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
	}

	public List<Modelo> getListaModelosFiltrados() {
		return ListaModelosFiltrados;
	}

	public void setListaModelosFiltrados(
			List<Modelo> listaModelosFiltrados) {
		ListaModelosFiltrados = listaModelosFiltrados;
	}

	public List<Marca> getListaMarcas() {
		return listaMarcas;
	}

	public void setListaMarcas(List<Marca> listaMarcas) {
		this.listaMarcas = listaMarcas;
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
			ModeloDAO dao = new ModeloDAO();
			dao.salvar(modeloCadastro);

			modeloCadastro = new Modelo();

			FacesUtil.adicionarMsgInfo("Modelo cadastrado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar ao incluir o Modelos: "
							+ ex.getMessage());
		}
	}

	public void novo() {
		modeloCadastro = new Modelo();

	}

	public void carregar() {
		try {
			ModeloDAO dao = new ModeloDAO();
			listaModelos = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar listar os modelos: "
							+ ex.getMessage());

		}
	}

	public void carregarCadastro() {
		try {

			if (id != null) {
				ModeloDAO dao = new ModeloDAO();
				modeloCadastro = dao.buscarPorCodigo(id);

			} else {
				modeloCadastro = new Modelo();
			}

			MarcaDAO dao = new MarcaDAO();
			listaMarcas = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados do modelos: "
							+ ex.getMessage());

		}

	}

	public void excluir() {

		try {
			ModeloDAO dao = new ModeloDAO();
			dao.excluir(modeloCadastro);

			FacesUtil.adicionarMsgInfo("Modelo removido com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar ao remover o modelo: "
							+ ex.getMessage());
		}
	}

	public void editar() {

		try {
			ModeloDAO dao = new ModeloDAO();
			dao.editar(modeloCadastro);

			FacesUtil.adicionarMsgInfo("Modelo editado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o modelo: "
					+ ex.getMessage());
		}
	}

	
	
}
