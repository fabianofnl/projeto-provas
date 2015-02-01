package br.com.sgpo.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.UsuarioDTO;
import br.com.sgpo.service.LoginService;
import br.com.sgpo.service.LoginServiceImpl;

/**
 * Classe com responsabilidade de verificar, autenticar e remover autenticação
 * do usuário no sistema.
 * 
 * @author Roseli
 * 
 */
@ManagedBean
@SessionScoped
public class LoginManagedBean implements Serializable {

	private static final long serialVersionUID = 1672291696958745459L;
	private static final Logger LOG = Logger.getLogger(LoginManagedBean.class);

	private UsuarioDTO usuario = new UsuarioDTO();
	private FuncionarioDTO funcionario = new FuncionarioDTO();

	/**
	 * Método com função de autenticar usuário no sistema
	 * 
	 * @return String
	 */
	public String logar() {

		try {
			LoginService loginService = new LoginServiceImpl();

			LOG.info("Executando processo de autenticação no sistema");
			LOG.info(usuario.getUsuario());
			funcionario = loginService.logar(usuario.getUsuario(),
					usuario.getSenha());
		} catch (SQLException e) {
			LOG.error("Erro na execução da query.", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Driver JDBC não encontrado.", e);
		}

		LOG.info("Resultado da autenticação: " + funcionario);

		if (funcionario != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(false);
			session.setAttribute("funcionario", funcionario);
			return "/pages/system/dashboard/dashboard";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Usuário ou senha inválidos"));
			limparSessao();
			return "/pages/login";
		}
	}

	/**
	 * Método que remove toda a sessão do usuário invalidando o mesmo, este é
	 * método que serve para sair do sistema
	 * 
	 * @return String
	 */
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		session.invalidate();
		limparSessao();
		return "/pages/login";
	}

	/**
	 * Método que altera a senha do usuário do sistema, recurso aplicado na tela
	 * de "Esqueci a senha"
	 * 
	 * @param event
	 */
	public void alterarSenha(ActionEvent event) {

		try {
			LoginService loginService = new LoginServiceImpl();
			String username = loginService.alterarSenha(funcionario);

			LOG.info("username:" + username);

			if (username != null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
								"Senha alterada com sucesso."));
				limparSessao();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Usuário ou senha inválidos"));
			}

		} catch (SQLException e) {
			LOG.error("Erro na execução da query.", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Driver JDBC não encontrado.", e);
		}
	}

	/**
	 * Método que limpa a sessão do usuário após trocar a senha na tela
	 * "Esqueci a senha"
	 */
	public void limparSessao() {
		usuario = new UsuarioDTO();
		funcionario = new FuncionarioDTO();
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}
}
