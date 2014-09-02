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
@WebServlet(value="/secure/cadastrarTemas")
public class CadastrarTemasController extends HttpServlet {

	private static final long serialVersionUID = -4294843051118528464L;
	private static final Logger LOG = Logger.getLogger(CadastrarTemasController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acessando classe cadastrar temas - método GET");
		req.getRequestDispatcher("/secure/cadastrarTemas.jsp").forward(req, resp);
	}
	
}
