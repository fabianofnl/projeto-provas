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
@WebServlet(value = "/secure/dashboard")
public class DashboardController extends HttpServlet {

	private static final long serialVersionUID = 8805206183250554983L;
	private static final Logger LOG = Logger
			.getLogger(DashboardController.class);

	@Override
	public void init() throws ServletException {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		LOG.info("Chamado classe Dashboard");
		req.getRequestDispatcher("/secure/dashboard.jsp").forward(req, resp);

	}

}
