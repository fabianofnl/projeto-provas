package br.com.sgpo.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.UsuarioDTO;
import br.com.sgpo.service.EsqueciSenhaService;
import br.com.sgpo.service.EsqueciSenhaServiceImpl;

/**
 * Classe referente a recuperação de senha.
 * 
 * @author Roseli
 * 
 */
@WebServlet(value = "/alterarSenha")
public class SenhaAlterarController extends HttpServlet {

	private static final long serialVersionUID = -2114908945534512046L;
	private static final Logger LOG = Logger.getLogger(SenhaAlterarController.class);

	private static final String VAR_TITULO_PAGINA = "tituloPagina";
	private static final String TITULO_PAGINA_ESQUECI_SENHA = "Provas Online | Esqueci Senha";
	private static final String VAR_MESSAGE = "msg";
	private static final String VAR_MESSAGE1 = "msg1";
	private static final String MESSAGE = "Usuário inexistente.";
	private static final String MESSAGE1 = "A nova senha foi enviado para seu e-mail.";
	private static final String VAR_USER = "user";
	private static final String VAR_EMAIL = "email";

	private EsqueciSenhaService esqueciSenhaService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		esqueciSenhaService = new EsqueciSenhaServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acessando Esqueci minha senha | Method GET");
		req.getRequestDispatcher("esqueciSenha.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acesso a URL: " + req.getContextPath() + " - method POST");

		String nomeUsuario = req.getParameter(VAR_USER);
		String email = req.getParameter(VAR_EMAIL);

		LOG.info("Usuario: " + nomeUsuario + " | E-mail: " + email);

		UsuarioDTO usuario = esqueciSenhaService.buscarUsuario(nomeUsuario, email);

		LOG.info("Objeto usuario: " + usuario);

		if (usuario != null) {
			esqueciSenhaService.alterarSenha(usuario);
			req.setAttribute(VAR_MESSAGE1, MESSAGE1);
		}else {
			req.setAttribute(VAR_MESSAGE, MESSAGE);
			req.setAttribute(VAR_USER, nomeUsuario);
			req.setAttribute(VAR_EMAIL, email);
		}

		req.setAttribute(VAR_TITULO_PAGINA, TITULO_PAGINA_ESQUECI_SENHA);
		req.getRequestDispatcher("esqueciSenha.jsp").forward(req, resp);
	}
}
