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
@WebServlet(value="/secure/apostilas")
public class ExamesApostilasController extends HttpServlet {

	private static final long serialVersionUID = 3923312831941167149L;
	private static final Logger LOG = Logger.getLogger(ExamesApostilasController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acessando classe Apostilas - método GET");
		req.getRequestDispatcher("/secure/apostilas.jsp").forward(req, resp);
	}
}
