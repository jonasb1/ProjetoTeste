package br.com.testeOpacidade.relatorios;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;
import br.com.testeOpacidade.dao.UsuarioDAO;
import br.com.testeOpacidade.dao.VeiculoDAO;
import br.com.testeOpacidade.model.Veiculo;

public class GerarRelatorio {
	public void geraRelatorio( ) throws JRException, Exception   
	{   
//	   VeiculoDAO dao = new VeiculoDAO();
//	   List<Veiculo> listaVeiculo = dao.listar();
//	  
//	  
//	      try {            
//	                    JasperDesign design = JasperManager.loadXmlDesign("/relatorio/todosVeiculos.jrxml" );    
//	                    JasperReport jr = JasperManager.compileReport(design);    
//	                        
//	                    HashMap parametros = new HashMap();  
//	                      
//	                    JasperPrint impressao = JasperManager.fillReport( jr, parametros, null );    
//	                        
//	                    JasperViewer jrviewer = new JasperViewer( impressao, false );    
//	                    jrviewer.setVisible(true);    
//	                    jrviewer.setDefaultCloseOperation(jrviewer.DISPOSE_ON_CLOSE);    
//	                }catch( Exception e) {    
//	                   
//	                    e.printStackTrace();    
//	                }   
//	}  
//	    
//	   public static void main(String[] args) throws JRException, Exception   
//	   {   
//	     new Jasper().geraRelatorio();   
//	   }   
	}
}
