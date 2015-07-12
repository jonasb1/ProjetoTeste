package br.com.lanchonete.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.lanchonete.dao.FornecedorDAO;
import br.com.lanchonete.model.Fornecedor;

@FacesConverter("fornecedorConverter")
public class FornecedorConverter implements Converter {
	
	//Quando eu seleciono no combo, recebo o id e retorno o objeto
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componente, String valor) {
		try{
			Long id= Long.parseLong(valor);
			
			FornecedorDAO dao = new FornecedorDAO();
			Fornecedor fornecedor=dao.buscarPorCodigo(id);
			
			return fornecedor;
			
		}catch (RuntimeException e) {
			return null;
		}
		
	}

	//Monta o campo , recebo o objeto e retorno o id.
	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, Object objeto) {
		try{
			Fornecedor fornecedor=(Fornecedor) objeto;
			Long id = fornecedor.getId();
			
			return id.toString();
			
		}catch(RuntimeException ex){
			
		}
		return null;
	}

}
