package br.com.sgpo.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.sgpo.model.Usuario;
import br.com.sgpo.service.LoginService;
import br.com.sgpo.service.LoginServiceImpl;

/**
 * Classe de autenticação do usuário do sistema.
 * 
 * @author Roseli
 * 
 */
@WebServlet(value = "/logon.jsp")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 5855906025905425623L;
	private static final Logger LOG = Logger.getLogger(LoginController.class);

	private static final String VAR_TITULO_PAGINA = "tituloPagina";
	private static final String TITULO_PAGINA_LOGIN = "Provas Online | Logon";
	private static final String TITULO_PAGINA_HOME = "SGPO | HOME";
	private static final String VAR_MESSAGE = "msg";
	private static final String MESSAGE = "Usuário ou senha inválidos.";
	private static final String VAR_USER = "user";
	private static final String VAR_PASS = "pass";

	private LoginService loginService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		loginService = new LoginServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		LOG.info("Acesso a URL: " + req.getContextPath()
				+ "/logon.jsp - method GET");

		req.setAttribute(VAR_TITULO_PAGINA, TITULO_PAGINA_LOGIN);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acesso a URL: " + req.getContextPath() + " - method POST");

		String nomeUsuario = req.getParameter("user");
		String senha = req.getParameter("pass");

		LOG.info("Usuario: " + nomeUsuario + " | Senha: " + senha);

		Usuario usuario = loginService.logar(nomeUsuario, senha);

		LOG.info("Objeto usuario: " + usuario);

		HttpSession session = null;
		String contextPath = req.getContextPath();

		if (usuario != null) {
			session = req.getSession(true);
			session.setAttribute("usuario", usuario);
		}else {
			req.setAttribute(VAR_TITULO_PAGINA, TITULO_PAGINA_LOGIN);
			req.setAttribute(VAR_MESSAGE, MESSAGE);
			req.setAttribute(VAR_USER, nomeUsuario);
			req.setAttribute(VAR_PASS, senha);
			resp.sendRedirect(contextPath + "/logon.jsp");
			//req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}

		req.setAttribute(VAR_TITULO_PAGINA, TITULO_PAGINA_HOME);
		//req.getRequestDispatcher("secure/home.jsp").forward(req, resp);
		resp.sendRedirect(contextPath + "/secure/home.jsp");
	}
}
