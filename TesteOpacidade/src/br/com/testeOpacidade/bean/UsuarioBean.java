package br.com.testeOpacidade.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.codec.digest.DigestUtils;
import br.com.testeOpacidade.dao.UsuarioDAO;
import br.com.testeOpacidade.model.Usuario;
import br.com.testeOpacidade.util.FacesUtil;


@ViewScoped
@ManagedBean
public class UsuarioBean {
	private Usuario usuarioCadastro;
	private List<Usuario> listaUsuarios;
	private List<Usuario> ListaUsuariosFiltrados;
	private String acao;
	private Long id;
	
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	public List<Usuario> getListaUsuariosFiltrados() {
		return ListaUsuariosFiltrados;
	}
	public void setListaUsuariosFiltrados(List<Usuario> listaUsuariosFiltrados) {
		ListaUsuariosFiltrados = listaUsuariosFiltrados;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void salvar() {

		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuarioCadastro.setSenha(DigestUtils.md5Hex(usuarioCadastro.getSenha()));
			dao.salvar(usuarioCadastro);

			usuarioCadastro = new Usuario();

			FacesUtil.adicionarMsgInfo("Usuários cadastrado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao incluir o Usuário: "
					+ ex.getMessage());
		}
	}

	public void novo() {
		usuarioCadastro = new Usuario();

	}

	public void carregar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuarios = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os usuarios: "
					+ ex.getMessage());

		}
	}

	public void carregarCadastro() {
		try {
			if (id != null) {
				
				UsuarioDAO dao = new UsuarioDAO();

				usuarioCadastro = dao.buscarPorCodigo(id);
			} else {

				usuarioCadastro = new Usuario();

			}
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar obter os dados do usuários: "
							+ ex.getMessage());

		}

	}

	public void excluir() {

		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.excluir(usuarioCadastro);

			FacesUtil.adicionarMsgInfo("Usuário removido com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar ao remover o usuário: "
					+ ex.getMessage());
		}
	}

	public void editar() {

		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuarioCadastro.setSenha(DigestUtils.md5Hex(usuarioCadastro.getSenha()));
			dao.editar(usuarioCadastro);

			FacesUtil.adicionarMsgInfo("Usuário editado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o usuário: "
					+ ex.getMessage());
		}
	}
	
}
	
	
	

