package br.com.lanchonete.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.lanchonete.dao.FuncionarioDAO;
import br.com.lanchonete.model.Funcionario;
import br.com.lanchonete.util.FacesUtil;

@ViewScoped
@ManagedBean
public class FuncionarioBean {
	private Funcionario funcionarioCadastro;
	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> ListaFuncionariosFiltrados;
	private String acao;
	private Long id;

	public Funcionario getFuncionarioCadastro() {

		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Funcionario> getListaFuncionariosFiltrados() {
		return ListaFuncionariosFiltrados;
	}

	public void setListaFuncionariosFiltrados(
			List<Funcionario> listaFuncionariosFiltrados) {
		ListaFuncionariosFiltrados = listaFuncionariosFiltrados;
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
			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro
					.getSenha()));
			dao.salvar(funcionarioCadastro);

			funcionarioCadastro = new Funcionario();

			FacesUtil.adicionarMsgInfo("Funcionário cadastrado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar ao incluir o funcionário: "
							+ ex.getMessage());
		}
	}

	public void novo() {
		funcionarioCadastro = new Funcionario();

	}

	public void carregar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			listaFuncionarios = dao.listar();

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar listar os funcionários: "
							+ ex.getMessage());

		}
	}

	public void carregarCadastro() {
		try {
			if (id != null) {

				FuncionarioDAO dao = new FuncionarioDAO();

				funcionarioCadastro = dao.buscarPorCodigo(id);
			} else {

				funcionarioCadastro = new Funcionario();

			}
		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar obter os dados do funcionário: "
							+ ex.getMessage());

		}

	}

	public void excluir() {

		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.excluir(funcionarioCadastro);

			FacesUtil.adicionarMsgInfo("Funcionário removido com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil
					.adicionarMsgErro("Erro ao tentar ao remover o funcionário: "
							+ ex.getMessage());
		}
	}

	public void editar() {

		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro
					.getSenha()));
			dao.editar(funcionarioCadastro);

			FacesUtil.adicionarMsgInfo("Funcionário editado com sucesso");

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar editar o funcionário: "
					+ ex.getMessage());
		}
	}

}
