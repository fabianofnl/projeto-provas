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

import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.service.QuestoesService;
import br.com.sgpo.service.QuestoesServiceImpl;
import br.com.sgpo.service.TemasService;
import br.com.sgpo.service.TemasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/alterarQuestao")
public class QuestoesAlterarController extends HttpServlet {

	private static final long serialVersionUID = 4474824008931216299L;

	private static final Logger LOG = Logger
			.getLogger(QuestoesAlterarController.class);

	private QuestoesService questoesService;
	private TemasService temasService;

	@Override
	public void init() throws ServletException {
		questoesService = new QuestoesServiceImpl();
		temasService = new TemasServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe alterar Questao - GET");

			String questaoId = req.getParameter("questaoId");

			if (!StringUtils.isNumeric(questaoId)) {
				req.setAttribute("msg", "Erro na aplicação.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			List<TemaDTO> listaTemas = temasService.listarTemas();
			QuestaoDTO questaoDTO = questoesService.buscarQuestaoPorId(Integer
					.parseInt(questaoId));

			req.setAttribute("listaTemas", listaTemas);
			req.setAttribute("questao", questaoDTO);

			req.getRequestDispatcher("/secure/questaoAlterar.jsp").forward(req,
					resp);

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
			LOG.info("Acessando classe Questao - método POST");

			QuestaoDTO questaoDTO = new QuestaoDTO();

			Integer questaoIdAntiga = Integer.parseInt(req
					.getParameter("questaoId"));
			questaoDTO.setTituloQuestao(req.getParameter("titulo"));
			questaoDTO.setDescricaoQuestao(req.getParameter("descricao"));
			questaoDTO.setTemaId(Integer.parseInt(req.getParameter("temaId")));

			questoesService.alterar(questaoDTO, questaoIdAntiga);

			HttpSession session = req.getSession(true);

			session.setAttribute("msg", "Questão alterado com sucesso!");
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
