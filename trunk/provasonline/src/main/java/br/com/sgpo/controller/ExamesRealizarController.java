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
@WebServlet(value="/secure/realizarExame")
public class ExamesRealizarController extends HttpServlet {

	private static final long serialVersionUID = 3923312831941167149L;
	private static final Logger LOG = Logger.getLogger(ExamesRealizarController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acessando classe Exame - método GET");
		req.getRequestDispatcher("/secure/realizarExame.jsp").forward(req, resp);
	}
}
