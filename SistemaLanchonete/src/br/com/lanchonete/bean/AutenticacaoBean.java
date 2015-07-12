package br.com.lanchonete.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.lanchonete.dao.FuncionarioDAO;
import br.com.lanchonete.model.Funcionario;
import br.com.lanchonete.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}

		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String autenticar() {
		try {
			FuncionarioDAO funcionarioDao= new FuncionarioDAO();
			funcionarioLogado= funcionarioDao.autenticar(funcionarioLogado.getCpf(), DigestUtils.md5Hex(funcionarioLogado.getSenha()));
			
			if(funcionarioLogado==null){
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
			funcionarioLogado=null;
		
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}
	
	
	
}
