package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.service.FuncionarioService;
import br.com.sgpo.service.FuncionarioServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/associarFuncionario")
public class EquipesAssociar extends HttpServlet {

	private static final long serialVersionUID = 4791543663963103553L;
	private static final Logger LOG = Logger.getLogger(EquipesAssociar.class);

	private FuncionarioService funcionarioService;

	@Override
	public void init() throws ServletException {
		funcionarioService = new FuncionarioServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			LOG.info("Acesso a URL: " + req.getContextPath()
					+ "/secure/associarFuncionario - method GET");

			List<FuncionarioDTO> listaGerentes = funcionarioService
					.listarGerentes();
			List<FuncionarioDTO> listaColaboradores = funcionarioService
					.listarColaboradores();

			req.setAttribute("listaGerentes", listaGerentes);
			req.setAttribute("listaColaboradores", listaColaboradores);

			req.getRequestDispatcher("/secure/funcionarioAssociar.jsp")
					.forward(req, resp);
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado.", e);
			req.setAttribute("msgType", "error");
			req.setAttribute("msg", "Erro durante o processamento!");
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		} catch (SQLException e) {
			LOG.error("Erro em alguma instrução SQL.", e);
			req.setAttribute("msgType", "error");
			req.setAttribute("msg", "Erro durante o processamento!");
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			String matGerente = req.getParameter("gerente");
			String[] matColString = req.getParameterValues("colaborador");

			Integer[] matColInteger = new Integer[matColString.length];

			for (int i = 0; i < matColString.length; i++) {
				matColInteger[i] = Integer.parseInt(matColString[i]);
			}

			funcionarioService.associarEquipes(Integer.parseInt(matGerente),
					matColInteger);

			req.setAttribute("msgType", "info");
			req.setAttribute("msg", "Associação efetuada com sucesso!");

			doGet(req, resp);

		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado.", e);
			req.setAttribute("msgType", "error");
			req.setAttribute("msg", "Erro durante o processamento!");
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		} catch (SQLException e) {
			LOG.error("Erro em alguma instrução SQL.", e);
			req.setAttribute("msgType", "error");
			req.setAttribute("msg", "Erro durante o processamento!");
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		}
	}
}
