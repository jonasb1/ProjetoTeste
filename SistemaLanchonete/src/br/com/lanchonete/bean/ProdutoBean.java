package br.com.lanchonete.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.lanchonete.dao.FornecedorDAO;
import br.com.lanchonete.dao.ProdutoDAO;
import br.com.lanchonete.dao.UnidadeDAO;
import br.com.lanchonete.model.Fornecedor;
import br.com.lanchonete.model.Produto;
import br.com.lanchonete.model.Unidade;
import br.com.lanchonete.util.FacesUtil;

@ViewScoped
@ManagedBean
public class ProdutoBean {
	private Produto produtoCadastro;
	private List<Produto> listaProdutos;
	private List<Produto> ListaProdutosFiltrados;
	private List<Fornecedor> listaFornecedores;
	private List<Unidade> listaUnidades;
	private String acao;
	private Long id;

	public Produto getProdutoCadastro() {
		if (produtoCadastro == null) {
			produtoCadastro = new Produto();
		}

		return produtoCadastro;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produto> getListaProdutosFiltrados() {
		return ListaProdutosFiltrados;
	}

	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		ListaProdutosFiltrados = listaProdutosFiltrados;
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
	
	public List<Fornecedor> getListaFornecedores() {
		return listaFornecedores;
	}

	public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
		this.listaFornecedores = listaFornecedores;
	}
	
	public List<Unidade> getListaUnidades() {
		return listaUnidades;
	}

	public void setListaUnidades(List<Unidade> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}

	public void salvar() {

		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.salvar(produtoCadastro);

			produtoCadastro = new Produto()	;

			FacesUtil.adicionarMsgInfo("Produto cadastrado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao incluir o produto: "
					+ ex.getMessage());
		}
	}

	public void novo() {
		produtoCadastro = new Produto();

	}

	public void carregar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listaProdutos = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os produto: "
					+ ex.getMessage());

		}
	}

	public void carregarCadastro() {
		try {
			
			if (id != null) {
				ProdutoDAO dao = new ProdutoDAO();
				produtoCadastro = dao.buscarPorCodigo(id);
				
			} else {
				produtoCadastro = new Produto();
			}
		
			FornecedorDAO dao = new FornecedorDAO();
			listaFornecedores=dao.listar();
		
			UnidadeDAO daounidade = new UnidadeDAO();
			listaUnidades=daounidade.listar();
			
			
		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados do produto: "
							+ ex.getMessage());

		}

	}

	public void excluir() {

		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(produtoCadastro);

			FacesUtil.adicionarMsgInfo("Produto removido com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao remover o produto: "
					+ ex.getMessage());
		}
	}

	public void editar() {

		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.editar(produtoCadastro);

			FacesUtil.adicionarMsgInfo("Produto editado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o produto: "
					+ ex.getMessage());
		}
	}

}
