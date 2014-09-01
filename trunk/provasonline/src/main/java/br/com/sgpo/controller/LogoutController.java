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

import br.com.sgpo.constants.SGPOConstants;

/**
 * Classe de logout do usuário do sistema.
 * 
 * @author Roseli
 * 
 */
@WebServlet(value = "/logout")
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = 5007262719284357114L;
	private static final Logger LOG = Logger.getLogger(LogoutController.class);

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acesso a URL: " + req.getContextPath() + " - method GET");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		LOG.info("Acesso a URL: " + req.getContextPath() + " - method POST");

		HttpServletRequest request = (HttpServletRequest) req;

		HttpSession session = request.getSession();
		session.removeAttribute(SGPOConstants.LOGGED_FUNCIONARIO);
		session.invalidate();

		String contextPath = req.getContextPath();

		LOG.info("Saindo da sessão");

		resp.sendRedirect(contextPath + "/logon");
	}
}
