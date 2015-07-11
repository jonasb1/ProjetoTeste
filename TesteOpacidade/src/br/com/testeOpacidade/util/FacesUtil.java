package br.com.testeOpacidade.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class FacesUtil {
	public static void adicionarMsgInfo(String mensagem) {

		FacesMessage faces = new FacesMessage(FacesMessage.SEVERITY_INFO,
				mensagem, mensagem);

		FacesContext facesContext = FacesContext.getCurrentInstance();

		// Uso quando preciso pegar informacoes do navegador
		ExternalContext externalContext = facesContext.getExternalContext();

		// Mensagem fica na Flase
		Flash flash = externalContext.getFlash();
		// Quero que a mensagem permaneca na sessao, por padrao ela é falso, ela ñ é destruida 
		flash.setKeepMessages(true);

		facesContext.addMessage(null, faces);

	}

	public static void adicionarMsgErro(String mensagem) {
		FacesMessage faces = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				mensagem, mensagem);

		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(true);

		facesContext.addMessage(null, faces);

	}

	public static String getParam(String nome) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();

		Map<String, String> parametros = externalContext
				.getRequestParameterMap();

		String valor = parametros.get(nome);

		return valor;
	}

	
}
