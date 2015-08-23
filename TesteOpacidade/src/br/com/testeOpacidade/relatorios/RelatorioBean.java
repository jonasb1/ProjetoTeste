package br.com.testeOpacidade.relatorios;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import br.com.testeOpacidade.dao.Teste_OpacidadeDAO;
import br.com.testeOpacidade.dao.VeiculoDAO;
import br.com.testeOpacidade.model.Teste_Opacidade;
import br.com.testeOpacidade.model.Veiculo;
import br.com.testeOpacidade.util.FacesUtil;

@ManagedBean
@ViewScoped
public class RelatorioBean {
	private List<Veiculo> listaVeiculos;
	private Veiculo veiculoSelecionado;
	public Veiculo getVeiculoSelecionado() {
		return veiculoSelecionado;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
		this.veiculoSelecionado = veiculoSelecionado;
	}

	private List<SelectItem> veiculoSelect;
	
	public void carregarCadastro() {
		try{
			VeiculoDAO dao = new VeiculoDAO();
			listaVeiculos = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados do veiculo: "
							+ ex.getMessage());

		}

	}
	
	
//	public List<SelectItem> getVeiculoSelect(){
//		
//		if (veiculoSelect == null){
//			veiculoSelect = new ArrayList<SelectItem>();
//			VeiculoDAO dao = new VeiculoDAO();
//			List<Veiculo> listaVeiculos =dao.listar();
//			
//			
//			if(listaVeiculos != null && ! listaVeiculos.isEmpty()){
//				SelectItem item;
//				
//				for(Veiculo veiculoLista : listaVeiculos){
//					item = new SelectItem(veiculoLista, veiculoLista.getNumero_frota());
//					veiculoSelect.add(item);
//				}
//			}
//		}
//		return veiculoSelect;
//	}
		
	
	public void geraRelatorio() throws JRException, IOException{
		
		System.out.println("Gerando relatório...");
		VeiculoDAO usuarioDAO = new VeiculoDAO();
		List listaUs = usuarioDAO.listar();
	
		JasperReport pathjrxml = JasperCompileManager.compileReport("relatorio/todosVeiculos.jasper");
		JasperPrint printReport = JasperFillManager.fillReport(pathjrxml, null, new JRBeanCollectionDataSource(listaUs));
		JasperExportManager.exportReportToPdfFile(printReport, "relatorio/todosVeiculos.pdf");
		System.out.println("Relatorio gerado");
}
		
		
	
	
}
