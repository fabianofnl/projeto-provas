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
@WebServlet(value = "/secure/funcionarioInativar")
public class FuncionarioInativarController extends HttpServlet {

	private static final long serialVersionUID = -3533861150730467156L;

	private static final Logger LOG = Logger
			.getLogger(FuncionarioInativarController.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acessando classe inativar - metodo POST");
	}
}
