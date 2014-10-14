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

import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.service.QuestoesService;
import br.com.sgpo.service.QuestoesServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/definirOpcao")
public class QuestoesDefinirOpcaoController extends HttpServlet {

	private static final long serialVersionUID = 4474824008931216299L;

	private static final Logger LOG = Logger
			.getLogger(QuestoesDefinirOpcaoController.class);

	private QuestoesService questoesService;

	@Override
	public void init() throws ServletException {
		questoesService = new QuestoesServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe definir Op��o - GET");

			String questaoId = req.getParameter("questaoId");

			if (!StringUtils.isNumeric(questaoId)) {
				req.setAttribute("msg", "Erro na aplica��o.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			QuestaoDTO questaoDTO = questoesService.buscarQuestaoPorId(Integer
					.parseInt(questaoId));

			questaoDTO.setListaOpcoes(questoesService
					.listarOpcoesPorQuestaoId(questaoDTO.getQuestaoId()));

			Integer tamanhoListaOpcoes = questaoDTO.getListaOpcoes().size();
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < tamanhoListaOpcoes; i++) {

				if (i == (tamanhoListaOpcoes - 1)) {
					sb.append((questaoDTO.getListaOpcoes().get(i)).getOpcaoId());
				} else {
					sb.append((questaoDTO.getListaOpcoes().get(i)).getOpcaoId());
					sb.append(",");
				}
			}

			req.setAttribute("opcoesId", sb.toString());
			req.setAttribute("questao", questaoDTO);

			req.getRequestDispatcher("/secure/questaoDefinirOpcao.jsp")
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
			LOG.info("Acessando classe Quest�oDefinirOpcao - m�todo POST");

			List<OpcaoDTO> listaOpcoes = new ArrayList<OpcaoDTO>();

			String opcoesId = req.getParameter("opcoesId");
			String[] arrayOpcoesId = opcoesId.split(",");

			Integer opcaoIdChecked = Integer
					.parseInt(req.getParameter("radio"));

			OpcaoDTO opcaoDTO = null;

			for (String stringOpcaoId : arrayOpcoesId) {
				opcaoDTO = new OpcaoDTO();

				if (opcaoIdChecked.equals(new Integer(stringOpcaoId))) {
					opcaoDTO.setOpcaoId(new Integer(stringOpcaoId));
					opcaoDTO.setFlag(true);
				} else {
					opcaoDTO.setOpcaoId(new Integer(stringOpcaoId));
					opcaoDTO.setFlag(false);
				}
				listaOpcoes.add(opcaoDTO);
			}

			questoesService.definirOpcao(listaOpcoes);

			HttpSession session = req.getSession(true);

			session.setAttribute("msg", "Op��o definido com sucesso!");
			session.setAttribute("msgType", "info");

			resp.sendRedirect(req.getContextPath() + "/secure/questoes");

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
}