package br.com.testeOpacidade.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testeOpacidade.dao.MarcaDAO;
import br.com.testeOpacidade.model.Marca;
import br.com.testeOpacidade.util.FacesUtil;

@ManagedBean
@ViewScoped
public class MarcaBean {
	private Marca marcaCadastro;
	private List<Marca> listaMarcas;
	private String acao;
	private Long id;

	public Marca getMarcaCadastro() {
		return marcaCadastro;
	}

	public void setMarcaCadastro(Marca marcaCadastro) {
		this.marcaCadastro = marcaCadastro;
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
			MarcaDAO dao = new MarcaDAO();
			dao.salvar(marcaCadastro);

			marcaCadastro = new Marca();

			FacesUtil.adicionarMsgInfo("Marca adicionada com sucesso");

		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao tentar incluir a marca "
					+ e.getMessage());

		}

	}

	public void novo() {
		marcaCadastro = new Marca();
	}

	public void carregar() {
		try {
			MarcaDAO dao = new MarcaDAO();
			listaMarcas = dao.listar();

		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao listar as marcas "
					+ e.getMessage());
		}

	}

	public void carregarCadastro() {
		try {
			if (id != null) {
				MarcaDAO dao = new MarcaDAO();
				marcaCadastro = dao.buscarPorCodigo(id);
			} else {
				marcaCadastro = new Marca();
			}
		} catch (RuntimeException e) {
			FacesUtil.adicionarMsgErro("Erro ao obter dados do cadastro "
					+ e.getMessage());
		}
	}

	public void excluir (){
		try{
			MarcaDAO dao = new MarcaDAO();
			dao.excluir(marcaCadastro);
			
			FacesUtil.adicionarMsgInfo("Marca removida com sucesso");
			
		}catch (RuntimeException e){
			FacesUtil.adicionarMsgErro("Erro ao excluir marca" +e.getMessage());
		}
		
	}
	
	public void editar(){
		try{
			MarcaDAO dao = new MarcaDAO();
			dao.editar(marcaCadastro);
				
			FacesUtil.adicionarMsgInfo("Marca editada com sucesso");
			
		}catch(RuntimeException e){
			FacesUtil.adicionarMsgErro("Erro ao editar marca"+e.getMessage());
		}
		
	}

}
