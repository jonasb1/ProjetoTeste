package br.com.testeOpacidade.teste;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TestandoGerarRelatorio {

//	public void gerarRelatorio() throws IOException, SQLException {
//
//		Session con = HibernateUtil.getSessionFactory().openSession();
//
//		VeiculoDAO veiculoDAO = new VeiculoDAO();
//
//		List<Veiculo> listaVeiculo = new ArrayList<Veiculo>();
//
//		listaVeiculo = veiculoDAO.listar();
//
//		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
//				listaVeiculo);
//
//		HashMap parameters = new HashMap();
//
//		try {
//
//			FacesContext facesContext = FacesContext.getCurrentInstance();
//
//			facesContext.responseComplete();
//
//			ServletContext scontext = (ServletContext) facesContext
//					.getExternalContext().getContext();
//
//			JasperPrint jasperPrint = JasperFillManager.fillReport(
//					scontext.getRealPath("/relatorio/todosVeiculos.jasper"),
//					parameters, ds);
//
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//			JRPdfExporter exporter = new JRPdfExporter();
//
//			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//
//			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//
//			exporter.exportReport();
//
//			byte[] bytes = baos.toByteArray();
//
//			if (bytes != null && bytes.length > 0) {
//
//				HttpServletResponse response = (HttpServletResponse) facesContext
//						.getExternalContext().getResponse();
//
//				response.setContentType("application/pdf");
//
//				response.setHeader("Content-disposition",
//						"inline; filename=\"relatorioPorData.pdf\"");
//
//				response.setContentLength(bytes.length);
//
//				ServletOutputStream outputStream = response.getOutputStream();
//
//				outputStream.write(bytes, 0, bytes.length);
//
//				outputStream.flush();
//
//				outputStream.close();
//
//				System.out.println("RelatorioGerado");
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		}
//	}
//
//	public void gerarRelatorio2() throws JRException, Exception {
//		/*
//		 * Chama o método getConnection para obter um objeto Connection,
//		 * utiliza-o para obter um objeto Statement e realiza uma consulta
//		 * armazendo o resultado em um ResultSet
//		 */
//
//		Session sessao = HibernateUtil.getSessionFactory().openSession();
//		
//		List<Veiculo> veiculos = null;
//		
//		
//			Query consulta = sessao.getNamedQuery("Veiculo.listar");
//			veiculos =consulta.list();
//			
//			
//		
//		/* Cria um JRResultSetDataSource com o resultado da consulta */
//		JRResultSetDataSource jrrs = new JRResultSetDataSource((ResultSet) veiculos);
//		/*
//		 * Cria um HashMap com parâmetros do relatório. Caso não seja atribuído
//		 * algum valor aos parâmetros estes assumem o valor padrão.
//		 */
//		Map parametros = new HashMap();
//		/*
//		 * Cria um arquivo .jrprint (relatório preenchido) utilizando o
//		 * Relatorio.jasper (design), os parâmetros e o JRResultSetDataSource
//		 * que é o resultado da consulta.
//		 */
//		JasperPrint jp = JasperFillManager.fillReport(
//				"/relatorio/todosVeiculos.jasper", parametros, jrrs);
//		/* Exibe o relatório */
//		JasperViewer.viewReport(jp);
//	}

}
