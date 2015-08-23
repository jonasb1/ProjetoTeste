package br.com.testeOpacidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.testeOpacidade.dao.SetorDAO;
import br.com.testeOpacidade.model.Setor;

@FacesConverter("setorConverter")
public class SetorConverter  implements Converter{
	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent componente, String valor) {
		try {
			Long id = Long.parseLong(valor);

			SetorDAO dao = new SetorDAO();
			Setor setor = dao.buscarPorCodigo(id);

			return setor;

		} catch (RuntimeException e) {
			return null;
		}

	}

	// Monta o campo , recebo o objeto e retorno o id.
	@Override
	public String getAsString(FacesContext facesContext,
			UIComponent componente, Object objeto) {
		try {
			Setor setor = (Setor) objeto;
			Long id = setor.getId();

			return id.toString();

		} catch (RuntimeException ex) {

		}
		return null;
	}
}
