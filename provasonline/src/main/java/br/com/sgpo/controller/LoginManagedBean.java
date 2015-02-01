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
 * Classe com responsabilidade de verificar, autenticar e remover autentica��o
 * do usu�rio no sistema.
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
	 * M�todo com fun��o de autenticar usu�rio no sistema
	 * 
	 * @return String
	 */
	public String logar() {

		try {
			LoginService loginService = new LoginServiceImpl();

			LOG.info("Executando processo de autentica��o no sistema");
			LOG.info(usuario.getUsuario());
			funcionario = loginService.logar(usuario.getUsuario(),
					usuario.getSenha());
		} catch (SQLException e) {
			LOG.error("Erro na execu��o da query.", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Driver JDBC n�o encontrado.", e);
		}

		LOG.info("Resultado da autentica��o: " + funcionario);

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
							"Usu�rio ou senha inv�lidos"));
			limparSessao();
			return "/pages/login";
		}
	}

	/**
	 * M�todo que remove toda a sess�o do usu�rio invalidando o mesmo, este �
	 * m�todo que serve para sair do sistema
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
	 * M�todo que altera a senha do usu�rio do sistema, recurso aplicado na tela
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
								"Usu�rio ou senha inv�lidos"));
			}

		} catch (SQLException e) {
			LOG.error("Erro na execu��o da query.", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Driver JDBC n�o encontrado.", e);
		}
	}

	/**
	 * M�todo que limpa a sess�o do usu�rio ap�s trocar a senha na tela
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
