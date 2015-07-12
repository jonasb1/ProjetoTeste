package br.com.lanchonete.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.lanchonete.dao.UnidadeDAO;

import br.com.lanchonete.model.Unidade;

@FacesConverter("unidadeConverter")
public class UnidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent componente, String valor) {
		try {
			Long id = Long.parseLong(valor);

			UnidadeDAO dao = new UnidadeDAO();
			Unidade unidade = dao.buscarPorCodigo(id);

			return unidade;

		} catch (RuntimeException e) {
			return null;
		}

	}

	// Monta o campo , recebo o objeto e retorno o id.
	@Override
	public String getAsString(FacesContext facesContext,
			UIComponent componente, Object objeto) {
		try {
			Unidade unidade = (Unidade) objeto;
			Long id = unidade.getId();

			return id.toString();

		} catch (RuntimeException ex) {

		}
		return null;
	}
}
