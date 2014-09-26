package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;
import br.com.sgpo.service.FuncionarioService;
import br.com.sgpo.service.FuncionarioServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/alterarFuncionario")
public class FuncionarioAlterarController extends HttpServlet {

	private static final long serialVersionUID = -4572498091016713686L;
	private static final Logger LOG = Logger
			.getLogger(FuncionarioAlterarController.class);

	private FuncionarioService funcionarioService;

	@Override
	public void init() throws ServletException {
		funcionarioService = new FuncionarioServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe alterar = metodo GET");

			String matricula = req.getParameter("matricula");

			if (!StringUtils.isNumeric(matricula)) {
				req.setAttribute("msg", "Matr�cula deve conter apenas d�gitos.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/secure/funcionario.jsp").forward(
						req, resp);
				return;
			}
			FuncionarioDTO funcionario = funcionarioService
					.buscarFuncionarioPorMatricula(Integer.parseInt(matricula));

			List<PerfilDTO> listaPerfis = listarPerfis();

			req.setAttribute("listaPerfis", listaPerfis);
			req.setAttribute("func", funcionario);

			req.getRequestDispatcher("/secure/alterarFuncionario.jsp").forward(
					req, resp);

		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados n�o encontrado.", e);
			req.setAttribute("msgType", "error");
			req.setAttribute("msg", "Erro durante o processamento!");
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		} catch (SQLException e) {
			LOG.error("Erro em alguma instru��o SQL.", e);
			req.setAttribute("msgType", "error");
			req.setAttribute("msg", "Erro durante o processamento!");
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acessando classe alterar = metodo POST");
	}

	private List<PerfilDTO> listarPerfis() throws ClassNotFoundException,
			SQLException {
		List<PerfilDTO> listaPerfis = new ArrayList<PerfilDTO>();
		listaPerfis = funcionarioService.listarPerfis();

		return listaPerfis;
	}
}
