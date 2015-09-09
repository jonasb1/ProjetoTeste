package br.com.testeOpacidade.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testeOpacidade.dao.ModeloDAO;
import br.com.testeOpacidade.dao.SetorDAO;
import br.com.testeOpacidade.dao.VeiculoDAO;
import br.com.testeOpacidade.model.Modelo;
import br.com.testeOpacidade.model.Setor;
import br.com.testeOpacidade.model.Veiculo;
import br.com.testeOpacidade.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VeiculoBean {
	private Veiculo veiculoCadastro;
	private List<Veiculo> listaVeiculos;
	private List<Veiculo> ListaVeiculosFiltrados;
	
	private List<Setor> listaSetores;
	private List<Modelo> listaModelos;

	private String acao;
	private Long id;
	
	public Veiculo getVeiculoCadastro() {
		if (veiculoCadastro == null) {
			veiculoCadastro = new Veiculo();
			
		}		
		return veiculoCadastro;
	}

	public void setVeiculoCadastro(Veiculo veiculoCadastro) {
		this.veiculoCadastro = veiculoCadastro;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public List<Veiculo> getListaVeiculosFiltrados() {
		return ListaVeiculosFiltrados;
	}

	public void setListaVeiculosFiltrados(List<Veiculo> listaVeiculosFiltrados) {
		ListaVeiculosFiltrados = listaVeiculosFiltrados;
	}

	public List<Setor> getListaSetores() {
		return listaSetores;
	}

	public void setListaSetores(List<Setor> listaSetores) {
		this.listaSetores = listaSetores;
	}

	public List<Modelo> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
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
//			veiculoCadastro.setUltimo_teste(new Date());
		
			Calendar c = Calendar.getInstance();
			c.setTime(veiculoCadastro.getUltimo_teste());
			//Todos os veiculos sao 180 exceto bombeiros
			c.add(Calendar.DATE,+180);
			
			veiculoCadastro.setProximoTeste(c.getTime());
			
			
			VeiculoDAO dao = new VeiculoDAO();
			dao.salvar(veiculoCadastro);

			veiculoCadastro = new Veiculo();
				
			FacesUtil.adicionarMsgInfo("Veiculo cadastrado com sucesso");
			
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao incluir o veiculo: "
					+ ex.getMessage());
		}
	}

	public void novo() {
		veiculoCadastro = new Veiculo();
		
	}

	public void carregar() {
		try {
			VeiculoDAO dao = new VeiculoDAO();
			listaVeiculos = dao.listar();
			
			
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os veiculos: "
					+ ex.getMessage());

		}
	}

	public void carregarCadastro() {
		try {

			if (id != null) {
				
				VeiculoDAO dao = new VeiculoDAO();
				veiculoCadastro = dao.buscarPorCodigo(id);

			} else {
				veiculoCadastro = new Veiculo();
				
			}

			ModeloDAO dao = new ModeloDAO();
			listaModelos = dao.listar();
						
			SetorDAO daoSetor = new SetorDAO();
			listaSetores = daoSetor.listar();
			
			
			
		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados dos modelos e setores: "
							+ ex.getMessage());

		}

	}

	public void excluir() {

		try {
			VeiculoDAO dao = new VeiculoDAO();
			dao.excluir(veiculoCadastro);

			FacesUtil.adicionarMsgInfo("Veiculo removido com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao remover o veiculos: "
					+ ex.getMessage());
		}
	}

	public void editar() {

		try {
			
			Calendar c = Calendar.getInstance();
			c.setTime(veiculoCadastro.getUltimo_teste());
			//Todos os veiculos sao 180 exceto bombeiros
			c.add(Calendar.DATE,+180);
			
			veiculoCadastro.setProximoTeste(c.getTime());
			
			
			VeiculoDAO dao = new VeiculoDAO();
			
			dao.editar(veiculoCadastro);

			FacesUtil.adicionarMsgInfo("Veiculo editado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o veiculo: "
					+ ex.getMessage());
		}
	}
	
	
	public void carregarPesquisaVeiculos() {
		try {
			
			
			VeiculoDAO dao = new VeiculoDAO();
			listaVeiculos = dao.listar();	
			Veiculo v= new Veiculo();
			int dias=0;
			for (int i=0;i<listaVeiculos.size();i++){
				v=listaVeiculos.get(i);
				
				Date dataHoje = new Date();
				
				
				System.out.println("Nº Frota: *"+v.getNumero_frota());
								
				dias = -1*((int) ((dataHoje.getTime() - v.getProximoTeste().getTime()) / 86400000L));
				dias++;
								
//				System.out.println("Data de hoje é :"+dataHoje);
//				
//				System.out.println("Data do proximo teste: "+v.getProximoTeste());
//				System.out.println("DIAS Restantes: "+dias);
//				
//				System.out.println("Data do ultimo teste: "+v.getUltimo_teste());
//				System.out.println("---------------------------------------------------------");
				
				//System.out.println("DIAS Restantes: ************"+v.getDiasRestantes());
//				
				
				v.setDiasRestantes(dias);
				
				dao.editar(v);
				
			}
						
			
		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados dos modelos e setores: "
							+ ex.getMessage());

		}

	}
	
	public void adicionarDias(){
		
		Date dataHoje = new java.util.Date();
		
		VeiculoDAO dao = new VeiculoDAO();
		listaVeiculos = dao.listar();
		
		int dias;
		
		Veiculo v = new Veiculo();
				
		for(int i=0; i<listaVeiculos.size();i++){
			dias = (int) ((dataHoje.getTime() - v.getUltimo_teste().getTime()) / 86400000L);
			
//			v.setDiasRestantes(dias);
			
		}
		
		
		
	}
	
	
	
	public void calcularProximoTeste(){
		Calendar c = Calendar.getInstance();
		VeiculoDAO dao = new VeiculoDAO();
		List<Veiculo> veiculos = dao.listar();
		
		Veiculo v = new Veiculo();
		
		for(int i =0; i<veiculos.size();i++){
			c.setTime(v.getUltimo_teste());
			c.add(Calendar.DATE,+100);
			
//			v.setProximoTeste(c);
		}
	}
	
	

}
