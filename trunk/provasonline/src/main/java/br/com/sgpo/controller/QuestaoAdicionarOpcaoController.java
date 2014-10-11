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

import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.service.QuestoesService;
import br.com.sgpo.service.QuestoesServiceImpl;


/**
 * @author Roseli
 *
 */
@WebServlet(value = "/secure/adicionarOpcao")
public class QuestaoAdicionarOpcaoController extends HttpServlet {

	private static final long serialVersionUID = -1433251825434458033L;
	private static final Logger LOG = Logger
			.getLogger(QuestaoAdicionarOpcaoController.class);

	private QuestoesService questoesService;

	@Override
	public void init() throws ServletException {
		questoesService = new QuestoesServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			LOG.info("Acesso a URL: " + req.getContextPath()
					+ "/secure/adicionarOpcao - method GET");

			String questaoId = req.getParameter("questaoId");

			if (!StringUtils.isNumeric(questaoId)) {
				req.setAttribute("msg", "Erro na aplicação.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			QuestaoDTO questao = questoesService.buscarQuestaoPorId(Integer
					.parseInt(questaoId));

			req.setAttribute("questao", questao);

			req.getRequestDispatcher("/secure/questaoAdicionarOpcao.jsp")
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

			LOG.info("Acesso a URL: " + req.getContextPath()
					+ "/secure/adicionarOpcao - method POST");

			OpcaoDTO opcao = new OpcaoDTO();
			opcao.setQuestaoId(Integer.parseInt(req.getParameter("questaoId")));
			opcao.setTituloOpcao(req.getParameter("tituloOpcao"));

			questoesService.gravarOpcao(opcao);
			
			HttpSession session = req.getSession(true);

			session.setAttribute("msg", "Opção adicionado com sucesso!");
			session.setAttribute("msgType", "info");

			resp.sendRedirect(req.getContextPath() + "/secure/questoes");

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
