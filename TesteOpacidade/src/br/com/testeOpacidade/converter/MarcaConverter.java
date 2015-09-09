package br.com.testeOpacidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.testeOpacidade.dao.MarcaDAO;
import br.com.testeOpacidade.model.Marca;

@FacesConverter("marcaConverter")
public class MarcaConverter implements Converter {

	// Quando eu seleciono no combo, recebo o id e retorno o objeto
	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent componente, String valor) {
		try {
			Long id = Long.parseLong(valor);

			MarcaDAO dao = new MarcaDAO();
			Marca marca = dao.buscarPorCodigo(id);

			return marca;

		} catch (RuntimeException e) {
			return null;
		}

	}

	// Monta o campo , recebo o objeto e retorno o id.
	@Override
	public String getAsString(FacesContext facesContext,
			UIComponent componente, Object objeto) {
		try {
			Marca marca = (Marca) objeto;
			Long id = marca.getId();

			return id.toString();

		} catch (RuntimeException ex) {

		}
		return null;
	}
}
