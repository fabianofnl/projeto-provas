package br.com.sgpo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/logon.jsp")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 5855906025905425623L;
	private static final Logger LOG = Logger.getLogger(LoginController.class);

	private static final String VAR_TITULO_PAGINA = "tituloPagina";
	private static final String TITULO_PAGINA = "Provas Online - Logon";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		LOG.info("Acessando metodo ao solicitar a requisito pela URL logon");
		LOG.info("Acesso a URL: " + req.getContextPath() + "/logon.jsp - method GET");

		req.setAttribute(VAR_TITULO_PAGINA, TITULO_PAGINA);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acesso a URL: " + req.getContextPath() + " - method POST");
	}
	
}
