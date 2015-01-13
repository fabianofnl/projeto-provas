package br.com.sgpo.controller;

import java.io.Serializable;

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
		
		LoginService loginService = new LoginServiceImpl();

		LOG.info("Executando processo de autentica��o no sistema");
		LOG.info(usuario.getUsuario());

		funcionario = loginService.logar(usuario.getUsuario(),
				usuario.getSenha());

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
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro", "Usu�rio ou senha inv�lidos"));
			return "/pages/login";
		}
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		session.invalidate();
		return "/pages/login";
	}
	
	public void teste(ActionEvent e){
		LOG.info("TESTE");
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
