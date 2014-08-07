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
	private static final String TITULO_PAGINA = "Provas Online - Logon";

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

		String nomeUsuario = req.getParameter("usuario");
		String senha = req.getParameter("senha");

		Usuario usuario = loginService.logar(nomeUsuario, senha);

		HttpSession session = null;

		if (usuario != null) {
			session = req.getSession(true);
			session.setAttribute("usuario", usuario);
		}

		req.setAttribute(VAR_TITULO_PAGINA, TITULO_PAGINA);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acesso a URL: " + req.getContextPath() + " - method POST");
	}

}
