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
@WebServlet(value = "/secure/alterar")
public class FuncionarioAlterarController extends HttpServlet {

	private static final long serialVersionUID = -4572498091016713686L;
	private static final Logger LOG = Logger
			.getLogger(FuncionarioAlterarController.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acessando classe alterar = metodo POST");
	}
}
