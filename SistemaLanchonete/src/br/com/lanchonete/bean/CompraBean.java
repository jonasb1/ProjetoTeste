package br.com.lanchonete.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.lanchonete.dao.CompraDAO;
import br.com.lanchonete.dao.FuncionarioDAO;
import br.com.lanchonete.dao.Itens_CompraDAO;
import br.com.lanchonete.dao.ProdutoDAO;

import br.com.lanchonete.filter.CompraFilter;

import br.com.lanchonete.model.Compra;
import br.com.lanchonete.model.Funcionario;
import br.com.lanchonete.model.Itens_Compra;
import br.com.lanchonete.model.Produto;

import br.com.lanchonete.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CompraBean {

	private List<Produto> listaProdutos;
	private List<Produto> ListaProdutosFiltrados;
	private List<Itens_Compra> listaItensCompra;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private CompraFilter filtro;
	private List<Compra> listaComprasFiltradas;

	private Compra compraCadastro;

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

	public List<Itens_Compra> getListaItensCompra() {
		if (listaItensCompra == null) {
			// Nao preciso colocar o nome do Array List
			listaItensCompra = new ArrayList<>();
		}

		return listaItensCompra;
	}

	public void setListaItensCompra(List<Itens_Compra> listaItensCompra) {
		this.listaItensCompra = listaItensCompra;
	}

	public Compra getCompraCadastro() {
		if (compraCadastro == null) {
			compraCadastro = new Compra();
			compraCadastro.setValor_total_compra(new BigDecimal("0.00"));
			compraCadastro.setQuantidade(0);
		}

		return compraCadastro;
	}

	public void setCompraCadastro(Compra compraCadastro) {
		this.compraCadastro = compraCadastro;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public CompraFilter getFiltro() {
		if (filtro == null) {
			filtro = new CompraFilter();
		}
		return filtro;
	}

	public void setFiltro(CompraFilter filtro) {
		this.filtro = filtro;
	}

	public List<Compra> getListaComprasFiltradas() {
		return listaComprasFiltradas;
	}

	public void setListaComprasFiltradas(List<Compra> listaComprasFiltradas) {
		this.listaComprasFiltradas = listaComprasFiltradas;
	}

	public void carregarProdutos() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listaProdutos = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os produto: "
					+ ex.getMessage());

		}
	}

	public void adicionar(Produto produto) {
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaItensCompra.size()
				&& posicaoEncontrada < 0; pos++) {
			Itens_Compra itemTemp = listaItensCompra.get(pos);

			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}

		}

		Itens_Compra item = new Itens_Compra();
		item.setProduto(produto);

		if (posicaoEncontrada < 0) {

			item.setQuantidade(1);
			item.setValor_parcial_compra(produto.getPreco());
			listaItensCompra.add(item);

		} else {

			Itens_Compra itemTemp = listaItensCompra.get(posicaoEncontrada);

			item.setQuantidade(itemTemp.getQuantidade() + 1);

			// Novo BigDecicmal multiplico pelo preco do produto
			item.setValor_parcial_compra(produto.getPreco().multiply(
					new BigDecimal(item.getQuantidade())));
			listaItensCompra.set(posicaoEncontrada, item);

			compraCadastro.setValor_total_compra(compraCadastro
					.getValor_total_compra().add(produto.getPreco()));
			compraCadastro.setQuantidade(compraCadastro.getQuantidade() + 1);

		}

		if (posicaoEncontrada < 0) {
			compraCadastro.setValor_total_compra(compraCadastro
					.getValor_total_compra().add(produto.getPreco()));
			compraCadastro.setQuantidade(compraCadastro.getQuantidade() + 1);
		}

	}

	public void remover(Itens_Compra itemCompra) {
		// Marca a posicao q eu encontro, -1 é nenhuma
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaItensCompra.size()
				&& posicaoEncontrada < 0; pos++) {
			Itens_Compra itemTemp = listaItensCompra.get(pos);

			if (itemTemp.getProduto().equals(itemCompra.getProduto())) {
				posicaoEncontrada = pos;
			}

		}

		if (posicaoEncontrada > -1) {
			listaItensCompra.remove(posicaoEncontrada);
			compraCadastro.setValor_total_compra(compraCadastro
					.getValor_total_compra().subtract(
							itemCompra.getValor_parcial_compra()));
			compraCadastro.setQuantidade(compraCadastro.getQuantidade()
					- itemCompra.getQuantidade());
		}

	}

	public void carregarDadosCompra() {
		compraCadastro.setHorario_compra(new Date());

		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario fun = dao.buscarPorCodigo(autenticacaoBean
				.getFuncionarioLogado().getId());

		compraCadastro.setFuncionario(fun);

	}

	public void salvar() {
		Integer verificarZero = new Integer(compraCadastro.getQuantidade());

		if (verificarZero == 0) {
			FacesUtil.adicionarMsgErro("Nenhum produto adicionado para compra");
		} else {

			try {
				CompraDAO compraDao = new CompraDAO();
				Long idCompra = compraDao.salvar(compraCadastro);
				Compra compraFK = compraDao.buscarPorCodigo(idCompra);

				for (Itens_Compra item : listaItensCompra) {
					item.setCompra(compraFK);
					Itens_CompraDAO itemDAO = new Itens_CompraDAO();
					itemDAO.salvar(item);

					Produto produto = item.getProduto();
					// Somando o produto
					int quantidade = produto.getQuantidade()
							+ item.getQuantidade();
					produto.setQuantidade(quantidade);

					ProdutoDAO produtoDAO = new ProdutoDAO();
					produtoDAO.editar(produto);

				}

				FacesUtil.adicionarMsgInfo("Compra Salva com sucesso");

				compraCadastro = new Compra();
				compraCadastro.setValor_total_compra(new BigDecimal(0));
				compraCadastro.setQuantidade(0);
				listaItensCompra = new ArrayList<Itens_Compra>();

			} catch (RuntimeException ex) {
				FacesUtil.adicionarMsgErro("Erro ao tentar salvar a compra: "
						+ ex.getMessage());

			}

		}
	}

	public void buscar() {
		try {
			CompraDAO compra = new CompraDAO();
			listaComprasFiltradas = compra.buscar(filtro);

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar buscar compras: "
					+ ex.getMessage());

		}
	}
}
