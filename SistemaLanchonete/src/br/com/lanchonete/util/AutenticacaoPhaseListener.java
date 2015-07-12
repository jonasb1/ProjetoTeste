package br.com.lanchonete.util;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


import br.com.lanchonete.bean.AutenticacaoBean;
import br.com.lanchonete.model.Funcionario;

@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		// Consigo pegar a pagina corrente
		UIViewRoot uiViewRoot = facesContext.getViewRoot();

		String paginaAtual = uiViewRoot.getViewId();
		// Minhas coloco aqui minhas paginas publicas
		boolean ehPaginaAutenticacao = paginaAtual
				.contains("autenticacao.xhtml");

		// Inversao Nao � a paginaAutenticacao, Verificar se nao estou se tiver
		// mais aulguma so ir adicionando
		if (!ehPaginaAutenticacao) {
			ExternalContext externalContext =facesContext.getExternalContext();
			//Mapa da Sessao
			Map<String,Object> mapa =externalContext.getSessionMap();
			AutenticacaoBean autenticacaoBean=(AutenticacaoBean) mapa.get("autenticacaoBean");
			
			Funcionario funcionario = autenticacaoBean.getFuncionarioLogado();
			
			if(funcionario.getFuncao()==null){
				FacesUtil.adicionarMsgErro("Funcion�rio n�o autenticado.");
				
				//Pegar informacaoes da Aplicacao 
				facesContext.getApplication();
				
				Application application=facesContext.getApplication();
				NavigationHandler navigationHandler = application.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null, "/pages/autenticacao.xhtml?faces-redirect=true");
				
			}
		}

	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
