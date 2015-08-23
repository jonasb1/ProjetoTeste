package br.com.testeOpacidade.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.testeOpacidade.dao.VeiculoDAO;
import br.com.testeOpacidade.model.Veiculo;

@FacesConverter("veiculoConverter")
public class VeiculoConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent componente, String valor) {
		try {
			Long id = Long.parseLong(valor);

			VeiculoDAO dao = new VeiculoDAO();
			Veiculo veiculo = dao.buscarPorCodigo(id);

			return veiculo;

		} catch (RuntimeException e) {
			return null;
		}

	}

	// Monta o campo , recebo o objeto e retorno o id.
	@Override
	public String getAsString(FacesContext facesContext,
			UIComponent componente, Object objeto) {
		try {
			Veiculo veiculo = (Veiculo) objeto;
			Long id = veiculo.getId();

			return id.toString();

		} catch (RuntimeException ex) {

		}
		return null;
	}
}
