package br.com.testeOpacidade.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testeOpacidade.dao.Teste_OpacidadeDAO;
import br.com.testeOpacidade.dao.VeiculoDAO;
import br.com.testeOpacidade.model.Teste_Opacidade;
import br.com.testeOpacidade.model.Veiculo;
import br.com.testeOpacidade.util.FacesUtil;


@ManagedBean
@ViewScoped
public class Teste_OpacidadeBean {
	private Teste_Opacidade testeOpacidadeCadastro;
	private List<Teste_Opacidade> listaTestes;
	private List<Teste_Opacidade> ListaTestesFiltrados;
	private List<Veiculo> listaVeiculos;
	private String acao;
	private Long id;

	
	public Teste_Opacidade getTesteOpacidadeCadastro() {
		return testeOpacidadeCadastro;
	}

	public void setTesteOpacidadeCadastro(Teste_Opacidade testeOpacidadeCadastro) {
		this.testeOpacidadeCadastro = testeOpacidadeCadastro;
	}

	public List<Teste_Opacidade> getListaTestes() {
		return listaTestes;
	}

	public void setListaTestes(List<Teste_Opacidade> listaTestes) {
		this.listaTestes = listaTestes;
	}

	public List<Teste_Opacidade> getListaTestesFiltrados() {
		return ListaTestesFiltrados;
	}

	public void setListaTestesFiltrados(
			List<Teste_Opacidade> listaTestesFiltrados) {
		ListaTestesFiltrados = listaTestesFiltrados;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
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
			Teste_OpacidadeDAO dao = new Teste_OpacidadeDAO();
			dao.salvar(testeOpacidadeCadastro);

			try{
				VeiculoDAO daoV = new VeiculoDAO();
				Veiculo v = testeOpacidadeCadastro.getVeiculo();
				v.setUltimo_teste(new Date());
				
				Calendar c = Calendar.getInstance();
				c.setTime(v.getUltimo_teste());
				
				if (testeOpacidadeCadastro.getVeiculo().getNumero_frota()==2023){
					c.add(Calendar.DATE,+360);
				}
				else if (testeOpacidadeCadastro.getVeiculo().getNumero_frota()==2065){
					c.add(Calendar.DATE,+365);
				}
				
				else if (testeOpacidadeCadastro.getVeiculo().getNumero_frota()==2068){
					c.add(Calendar.DATE,+365);
				}
				
				else if (testeOpacidadeCadastro.getVeiculo().getNumero_frota()==2087){
					c.add(Calendar.DATE,+360);
				}
				
				else{
					c.add(Calendar.DATE,+180);
				}
								
				v.setProximoTeste(c.getTime());
				
				daoV.editar(v);
				
			} catch (RuntimeException ex) {
				FacesUtil.adicionarMsgErro("Erro ao tentar a data do veiculo: "
						+ ex.getMessage());
			}
			
			testeOpacidadeCadastro = new Teste_Opacidade();

			
			
			FacesUtil.adicionarMsgInfo("Teste cadastrado com sucesso");
	
			
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao incluir o Teste: "
					+ ex.getMessage());
		}
	}

	public void novo() {
		testeOpacidadeCadastro = new Teste_Opacidade();

	}

	public void carregar() {
		try {
			Teste_OpacidadeDAO dao = new Teste_OpacidadeDAO();
			listaTestes = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os testes: "
					+ ex.getMessage());

		}
	}

	public void carregarCadastro() {
		try {

			if (id != null) {
				Teste_OpacidadeDAO dao = new Teste_OpacidadeDAO();
				testeOpacidadeCadastro = dao.buscarPorCodigo(id);

			} else {
				testeOpacidadeCadastro = new Teste_Opacidade();
				testeOpacidadeCadastro.setData_teste(new Date());
			}

			VeiculoDAO dao = new VeiculoDAO();
			listaVeiculos = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados do veiculo: "
							+ ex.getMessage());

		}

	}

}
