package br.com.lanchonete.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.lanchonete.dao.EstadoDAO;
import br.com.lanchonete.model.Estado;


@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter {
	
	//Quando eu seleciono no combo, recebo o id e retorno o objeto
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componente, String valor) {
		try{
			Long id= Long.parseLong(valor);
			
			EstadoDAO dao = new EstadoDAO();
			Estado estado=dao.buscarPorCodigo(id);
			
			return estado;
			
		}catch (RuntimeException e) {
			return null;
		}
		
	}

	//Monta o campo , recebo o objeto e retorno o id.
	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, Object objeto) {
		try{
			Estado estado=(Estado) objeto;
			Long id = estado.getId();
			
			return id.toString();
			
		}catch(RuntimeException ex){
			
		}
		return null;
	}
}
