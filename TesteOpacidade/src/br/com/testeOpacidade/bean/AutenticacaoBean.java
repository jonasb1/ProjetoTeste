package br.com.testeOpacidade.bean;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.testeOpacidade.dao.UsuarioDAO;
import br.com.testeOpacidade.model.Usuario;
import br.com.testeOpacidade.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) {
			usuarioLogado = new Usuario();
		}

		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String autenticar() {
		try {
			UsuarioDAO usuarioDao= new UsuarioDAO();
			usuarioLogado= usuarioDao.autenticar(usuarioLogado.getCodigoFuncionario(),usuarioLogado.getSenha());
			
			if(usuarioLogado==null){
				FacesUtil.adicionarMsgErro("CPF e/ou senha inválidos");
				//Vou ficar na mesma página
				return null;
				
			}else{
				FacesUtil.adicionarMsgInfo("Funcionário autenticado com sucesso");
				return "/pages/principal.xhtml?faces-redirect=true";
			}
						
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar autenticar no sistema: "	+ ex.getMessage());
			return null;
		}
		
		
	}
	
	public String sair(){
			usuarioLogado=null;
		
		return "/pages/autenticacao2.xhtml?faces-redirect=true";
	}
	
	
}
