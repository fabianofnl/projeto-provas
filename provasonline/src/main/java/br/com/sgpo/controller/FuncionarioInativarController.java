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
import javax.servlet.http.HttpSession;

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
@WebServlet(value = "/secure/inativarFuncionario")
public class FuncionarioInativarController extends HttpServlet {

	private static final long serialVersionUID = -3533861150730467156L;

	private static final Logger LOG = Logger
			.getLogger(FuncionarioInativarController.class);

	private FuncionarioService funcionarioService;

	@Override
	public void init() throws ServletException {
		funcionarioService = new FuncionarioServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe inativar - metodo GET");

			String matricula = req.getParameter("matricula");

			if (!StringUtils.isNumeric(matricula)) {
				req.setAttribute("msg", "Erro na aplica��o.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);

				LOG.error("Valor do campo matricula nao eh numerico - valor[ "
						+ matricula + " ]");

				return;
			}
			FuncionarioDTO funcionario = funcionarioService
					.buscarFuncionarioPorMatricula(Integer.parseInt(matricula));

			List<PerfilDTO> listaPerfis = listarPerfis();

			req.setAttribute("listaPerfis", listaPerfis);
			req.setAttribute("func", funcionario);

			req.getRequestDispatcher("/secure/funcionarioInativar.jsp")
					.forward(req, resp);

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

		try {
			LOG.info("Acessando classe inativar - metodo POST");

			String matricula = req.getParameter("matricula");

			if (!StringUtils.isNumeric(matricula)) {
				req.setAttribute("msg", "Erro na aplica��o.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);

				LOG.error("Valor do campo matricula nao eh numerico - valor[ "
						+ matricula + " ]");

				return;
			}

			funcionarioService.inativar(Integer.parseInt(matricula));

			HttpSession session = req.getSession(true);

			session.setAttribute("msgType", "info");
			session.setAttribute("msg", "Funcionario inativado com sucesso!");

			resp.sendRedirect(req.getContextPath() + "/secure/funcionario");

			// req.setAttribute("msgType", "info");
			// req.setAttribute("msg", "Funcionario inativado com sucesso!");

			// req.getRequestDispatcher("/secure/funcionarioInativar.jsp")
			// .forward(req, resp);

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

	private List<PerfilDTO> listarPerfis() throws ClassNotFoundException,
			SQLException {
		List<PerfilDTO> listaPerfis = new ArrayList<PerfilDTO>();
		listaPerfis = funcionarioService.listarPerfis();

		return listaPerfis;
	}
}