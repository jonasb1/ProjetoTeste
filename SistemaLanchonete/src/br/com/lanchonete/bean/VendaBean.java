package br.com.lanchonete.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.lanchonete.dao.FuncionarioDAO;
import br.com.lanchonete.dao.Itens_VendaDAO;
import br.com.lanchonete.dao.ProdutoDAO;
import br.com.lanchonete.dao.VendaDAO;
import br.com.lanchonete.filter.VendaFilter;
import br.com.lanchonete.model.Funcionario;
import br.com.lanchonete.model.Itens_Venda;
import br.com.lanchonete.model.Produto;
import br.com.lanchonete.model.Venda;
import br.com.lanchonete.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private List<Produto> listaProdutos;
	private List<Produto> ListaProdutosFiltrados;
	private List<Itens_Venda> listaItensVenda;

	// Pego o managed bean q esta instaciado e jogo na variavel
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;
	private Venda vendaCadastro;

	private VendaFilter filtro;
	private List<Venda> listaVendasFiltradas;

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

	public List<Itens_Venda> getListaItensVenda() {
		if (listaItensVenda == null) {
			// Nao preciso colocar o nome do Array List
			listaItensVenda = new ArrayList<>();
		}

		return listaItensVenda;
	}

	public void setListaItensVenda(List<Itens_Venda> listaItensVenda) {
		this.listaItensVenda = listaItensVenda;
	}

	public Venda getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setValor_total_venda(new BigDecimal("0.00"));
			vendaCadastro.setQuantidade(0);
			vendaCadastro.setValorTroco(new BigDecimal("0.00"));

		}

		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public VendaFilter getFiltro() {
		if (filtro == null) {
			filtro = new VendaFilter();
		}

		return filtro;
	}

	public void setFiltro(VendaFilter filtro) {
		this.filtro = filtro;
	}

	public List<Venda> getListaVendasFiltradas() {
		return listaVendasFiltradas;
	}

	public void setListaVendasFiltradas(List<Venda> listaVendasFiltradas) {
		this.listaVendasFiltradas = listaVendasFiltradas;
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

		for (int pos = 0; pos < listaItensVenda.size() && posicaoEncontrada < 0; pos++) {
			Itens_Venda itemTemp = listaItensVenda.get(pos);

			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}

		}

		int testeQuantidade = produto.getQuantidade();
		System.out.println("Quantidade ProdutoAAAAAAAAAAAAAAAA "
				+ testeQuantidade);

		Itens_Venda item = new Itens_Venda();
		item.setProduto(produto);

		if (produto.getQuantidade() != 0) {

			if (posicaoEncontrada < 0) {

				item.setQuantidade(1);
				item.setValor_parcial_venda(produto.getPreco());
				listaItensVenda.add(item);

			} else {

				Itens_Venda itemTemp = listaItensVenda.get(posicaoEncontrada);

				item.setQuantidade(itemTemp.getQuantidade() + 1);

				if (item.getQuantidade() <= testeQuantidade) {

					// Novo BigDecicmal multiplico pelo preco do produto
					item.setValor_parcial_venda(produto.getPreco().multiply(
							new BigDecimal(item.getQuantidade())));
					listaItensVenda.set(posicaoEncontrada, item);

					System.out
							.println("IGUAL OS VALORES AAA");

					vendaCadastro.setValor_total_venda(vendaCadastro
							.getValor_total_venda().add(produto.getPreco()));
					vendaCadastro
							.setQuantidade(vendaCadastro.getQuantidade() + 1);

				} else {
					FacesUtil
							.adicionarMsgErro("Quantidade adicionada maior do que a do estoque");

				}

			}

			if (posicaoEncontrada < 0) {
				vendaCadastro.setValor_total_venda(vendaCadastro
						.getValor_total_venda().add(produto.getPreco()));
				vendaCadastro.setQuantidade(vendaCadastro.getQuantidade() + 1);
			}

		} else {
			FacesUtil.adicionarMsgErro("Produto zerado em estoque");

		}

	}

	public void remover(Itens_Venda itemVenda) {
		// Marca a posicao q eu encontro, -1 é nenhuma
		int posicaoEncontrada = -1;

		// ListaItensVenda.size = Quebra a repeticao quando nao tem nada
		// posicaoEncontrada<0 = caso eu encontro eu quebro a repeticao
		for (int pos = 0; pos < listaItensVenda.size() && posicaoEncontrada < 0; pos++) {
			Itens_Venda itemTemp = listaItensVenda.get(pos);

			if (itemTemp.getProduto().equals(itemVenda.getProduto())) {
				posicaoEncontrada = pos;
			}

		}

		if (posicaoEncontrada > -1) {
			listaItensVenda.remove(posicaoEncontrada);
			vendaCadastro.setValor_total_venda(vendaCadastro
					.getValor_total_venda().subtract(
							itemVenda.getValor_parcial_venda()));
			vendaCadastro.setQuantidade(vendaCadastro.getQuantidade()
					- itemVenda.getQuantidade());
		}

	}

	public void carregarDadosVenda() {
			double valorTeste = vendaCadastro.getValor_total_venda().doubleValue();
		
			if(valorTeste==0){
				FacesUtil.adicionarMsgErro("Nenhum produto");
			}
			else{
			vendaCadastro.setHorario_venda(new Date());
			FuncionarioDAO dao = new FuncionarioDAO();
			Funcionario fun = dao.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getId());

			vendaCadastro.setFuncionario(fun);
			}
	}

	public void salvar() {

		Integer verificarZero = new Integer(vendaCadastro.getQuantidade());

		if (verificarZero == 0) {
			FacesUtil.adicionarMsgErro("Nenhum produto adicionado para venda");
		} else {

			try {
				VendaDAO vendaDao = new VendaDAO();
				Long idVenda = vendaDao.salvar(vendaCadastro);
				Venda vendaFK = vendaDao.buscarPorCodigo(idVenda);

				for (Itens_Venda item : listaItensVenda) {
					item.setVenda(vendaFK);
					Itens_VendaDAO itemDAO = new Itens_VendaDAO();
					itemDAO.salvar(item);

					Produto produto = item.getProduto();
					int quantidade = produto.getQuantidade()
							- item.getQuantidade();
					produto.setQuantidade(quantidade);

					ProdutoDAO produtoDAO = new ProdutoDAO();
					produtoDAO.editar(produto);

				}

				FacesUtil.adicionarMsgInfo("Venda Salva com sucesso");

				vendaCadastro = new Venda();
				vendaCadastro.setValor_total_venda(new BigDecimal(0));
				vendaCadastro.setQuantidade(0);
				listaItensVenda = new ArrayList<Itens_Venda>();
				vendaCadastro.setValorTroco(new BigDecimal("0.00"));

			} catch (RuntimeException ex) {
				FacesUtil.adicionarMsgErro("Erro ao tentar salvar a venda: "
						+ ex.getMessage());

			}

		}
	}

	public void buscar() {
		try {
			VendaDAO venda = new VendaDAO();
			listaVendasFiltradas = venda.buscar(filtro);

			// for(Venda vendas:listaVendasFiltradas){

			// System.out.println(vendas);
			// }

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar buscar vendas: "
					+ ex.getMessage());

		}
	}

	public void calcularTroco() {
		Double valorRecebido = vendaCadastro.getValorRecebido().doubleValue();
		Double valorVenda = vendaCadastro.getValor_total_venda().doubleValue();
		Double valorRestante = valorVenda - valorRecebido;
		Double valorCorrigido=formatarDecimal(valorRestante);
		
		
		if (vendaCadastro.getValorRecebido().equals(
				vendaCadastro.getValor_total_venda())) {
			FacesUtil.adicionarMsgErro("Valor Recebido igual ao da Venda");

		}

		else if (valorRecebido < valorVenda) {
			FacesUtil.adicionarMsgErro("Ainda faltam R$ " + valorCorrigido);
			vendaCadastro.setValorTroco(new BigDecimal("0.00"));
		}
		
		else if (valorVenda==0){
			FacesUtil.adicionarMsgErro("Nenhum produto foi adcionado");
		}

		else {
			vendaCadastro.setValorTroco(vendaCadastro.getValor_total_venda()
					.subtract(vendaCadastro.getValorRecebido()));
		}
		
	}

	
	private double formatarDecimal(double valor) {
		DecimalFormat df = new DecimalFormat("#.##");
		
		String temp = df.format(valor).replace(",", ".");
		
		return Double.parseDouble(temp);
		
	
	}
	
	
	
}
