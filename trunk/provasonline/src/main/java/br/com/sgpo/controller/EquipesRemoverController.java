package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.service.FuncionarioService;
import br.com.sgpo.service.FuncionarioServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/removerColaborador")
public class EquipesRemoverController extends HttpServlet {

	private static final long serialVersionUID = 1803345723538484813L;
	private static final Logger LOG = Logger
			.getLogger(EquipesRemoverController.class);

	private FuncionarioService funcionarioService;

	@Override
	public void init() throws ServletException {
		funcionarioService = new FuncionarioServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			LOG.info("Acessando classe Equipe Remover Colaborador- metodo GET");

			String matricula = req.getParameter("matricula");

			if (!StringUtils.isNumeric(matricula)) {
				req.setAttribute("msg", "Erro na aplicação.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);

				LOG.error("Valor do campo matricula nao eh numerico - valor[ "
						+ matricula + " ]");

				return;
			}
			FuncionarioDTO funcionario = funcionarioService
					.buscarFuncionarioPorMatricula(Integer.parseInt(matricula));

			req.setAttribute("func", funcionario);

			req.getRequestDispatcher("/secure/equipeRemoverColaborador.jsp")
					.forward(req, resp);

		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado.", e);
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		} catch (SQLException e) {
			LOG.error("Erro em alguma instrução SQL.", e);
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe Equipe Remover Colaborador- metodo POST");

			String matricula = req.getParameter("matricula");

			if (!StringUtils.isNumeric(matricula)) {
				req.setAttribute("msg", "Erro na aplicação.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);

				LOG.error("Valor do campo matricula nao eh numerico - valor[ "
						+ matricula + " ]");

				return;
			}

			funcionarioService.removerColaborador(Integer.parseInt(matricula));

			HttpSession session = req.getSession(true);

			session.setAttribute("msgType", "info");
			session.setAttribute("msg", "Colaborador removido com sucesso!");

			resp.sendRedirect(req.getContextPath() + "/secure/listarEquipes");

			// req.setAttribute("msgType", "info");
			// req.setAttribute("msg", "Funcionario inativado com sucesso!");

			// req.getRequestDispatcher("/secure/funcionarioInativar.jsp")
			// .forward(req, resp);

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
