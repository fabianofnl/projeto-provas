package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;
import br.com.sgpo.service.QuestoesService;
import br.com.sgpo.service.QuestoesServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/adicionarQuestoesProva")
public class ProvasAdicionarQuestoesController extends HttpServlet {

	private static final long serialVersionUID = -1433251825434458033L;
	private static final Logger LOG = Logger
			.getLogger(ProvasAdicionarQuestoesController.class);

	private ProvasService provasService;
	private QuestoesService questoesService;

	@Override
	public void init() throws ServletException {
		provasService = new ProvasServiceImpl();
		questoesService = new QuestoesServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			LOG.info("Acesso a URL: " + req.getContextPath()
					+ "/secure/adicionarQuestoesProvas - method GET");

			String provaId = req.getParameter("provaId");

			if (!StringUtils.isNumeric(provaId)) {
				req.setAttribute("msg", "Erro na aplicação.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			List<QuestaoDTO> listaQuestoes = questoesService
					.listarQuestoesSemProvas();

			ProvaDTO prova = provasService.buscarProvaPorId(Integer
					.parseInt(provaId));

			req.setAttribute("listaQuestoes", listaQuestoes);
			req.setAttribute("prova", prova);

			req.getRequestDispatcher("/secure/provasAdicionarQuestoes.jsp")
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

			String provaId = req.getParameter("provaId");
			String[] questoesIdString = req.getParameterValues("questoesId");

			Integer[] questoesIdInteger = new Integer[questoesIdString.length];

			for (int i = 0; i < questoesIdString.length; i++) {
				questoesIdInteger[i] = Integer.parseInt(questoesIdString[i]);
			}

			provasService.associarProvaQuestoes(Integer.parseInt(provaId),
					questoesIdInteger);

			HttpSession session = req.getSession(true);

			session.setAttribute("msg", "Questão(ões) adicionado(s) com sucesso!");
			session.setAttribute("msgType", "info");

			resp.sendRedirect(req.getContextPath() + "/secure/provas");

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
