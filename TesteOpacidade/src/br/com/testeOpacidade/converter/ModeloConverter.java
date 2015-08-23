package br.com.testeOpacidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.testeOpacidade.dao.ModeloDAO;
import br.com.testeOpacidade.model.Modelo;

@FacesConverter("modeloConverter")
public class ModeloConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent componente, String valor) {
		try {
			Long id = Long.parseLong(valor);

			ModeloDAO dao = new ModeloDAO();
			Modelo modelo = dao.buscarPorCodigo(id);

			return modelo;

		} catch (RuntimeException e) {
			return null;
		}

	}

	// Monta o campo , recebo o objeto e retorno o id.
	@Override
	public String getAsString(FacesContext facesContext,
			UIComponent componente, Object objeto) {
		try {
			Modelo modelo = (Modelo) objeto;
			Long id = modelo.getId();

			return id.toString();

		} catch (RuntimeException ex) {

		}
		return null;
	}
}
