package br.com.sgpo.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.sgpo.model.Usuario;
import br.com.sgpo.service.EsqueciSenhaService;
import br.com.sgpo.service.EsqueciSenhaServiceImpl;

/**
 * Classe referente a recupera��o de senha.
 * 
 * @author Roseli
 * 
 */
@WebServlet(value = "/esqueciSenha")
public class EsqueciSenhaController extends HttpServlet {

	private static final long serialVersionUID = -2114908945534512046L;
	private static final Logger LOG = Logger.getLogger(EsqueciSenhaController.class);

	private static final String VAR_TITULO_PAGINA = "tituloPagina";
	private static final String TITULO_PAGINA_ESQUECI_SENHA = "Provas Online | Esqueci Senha";
	private static final String VAR_MESSAGE = "msg";
	private static final String VAR_MESSAGE1 = "msg1";
	private static final String MESSAGE = "Usu�rio inexistente.";
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
		String contextPath = req.getContextPath();
		resp.sendRedirect(contextPath + "/esqueciSenha.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acesso a URL: " + req.getContextPath() + " - method POST");

		String nomeUsuario = req.getParameter(VAR_USER);
		String email = req.getParameter(VAR_EMAIL);

		LOG.info("Usuario: " + nomeUsuario + " | E-mail: " + email);

		Usuario usuario = esqueciSenhaService.buscarUsuario(nomeUsuario, email);

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
		//resp.sendRedirect(contextPath + "/esqueciSenha.jsp");
	}
}
