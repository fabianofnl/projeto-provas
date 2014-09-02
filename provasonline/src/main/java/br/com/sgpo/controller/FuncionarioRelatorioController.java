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
@WebServlet(value = "/secure/funcionarioRelatorio")
public class FuncionarioRelatorioController extends HttpServlet {

	private static final long serialVersionUID = -1877113256158709477L;

	private static final Logger LOG = Logger
			.getLogger(FuncionarioRelatorioController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acessando classe funcionario relatório - metodo GET");
		req.getRequestDispatcher("/secure/funcionarioRelatorio.jsp").forward(req, resp);
	}

}
