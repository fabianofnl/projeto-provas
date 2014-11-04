package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.sgpo.constants.SGPOConstants;
import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;
import br.com.sgpo.service.QuestoesService;
import br.com.sgpo.service.QuestoesServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/realizarProva")
public class ProvaRealizarNotificacaoController extends HttpServlet {

	private static final long serialVersionUID = 3923312831941167149L;
	private static final Logger LOG = Logger
			.getLogger(ProvaRealizarNotificacaoController.class);

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
			LOG.info("Acessando classe ProvaRealizar - m�todo GET");

			String provaId = req.getParameter("provaId");
			String agendaId = req.getParameter("agendaId");

			if (!StringUtils.isNumeric(provaId)
					&& !StringUtils.isNumeric(agendaId)) {
				req.setAttribute("msg", "Erro na aplica��o.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			ProvaDTO prova = provasService.buscarProvaPorId(Integer
					.parseInt(provaId));
			AgendaDTO agenda = provasService.buscarAgendaPorId(Integer
					.parseInt(agendaId));

			ProvaRealizadaDTO provaRealizada = provasService
					.buscarProvaRealizadaPorAgendaId(agenda.getAgendaId());

			if (provaRealizada == null) {
				req.setAttribute("prova", prova);
				req.setAttribute("agenda", agenda);
				req.setAttribute("msgType", "info");
				req.setAttribute(
						"msg",
						"Voc� dever� realizar a prova <strong>"+ prova.getTitulo() +"</strong>, ap�s o in�cio "
								+ "cancelar. <br> "
								+ "Certifique-se de que n�o haver� problemas durante a realiza��o da prova, "
								+ "caso contr�rio voc� poder� zerar.<br><br> "
								+ "Se houver problemas durante a prova, como sair da p�gina da prova, voc� "
								+ "poder� retomar a prova desde que o tempo n�o tenha excedido, por�m, "
								+ "perder� o preenchimento das quest�es.");

			} else if (provaRealizada.getDataHoraFim().getTime()
					- provaRealizada.getDataHoraInicio().getTime() <= SGPOConstants.TEMPO_DE_PROVA) {

				doPost(req, resp);
				return;

			} else {
				req.setAttribute("msgType", "warn");
				req.setAttribute("msg",
						"Esta prova n�o existe ou voc� j� realizou");
			}

			req.getRequestDispatcher("/secure/provaRealizarNotificacao.jsp")
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
			LOG.info("Acessando classe ProvaRealizar - m�todo POST");

			ProvaRealizadaDTO provaRealizada = new ProvaRealizadaDTO();
			AgendaDTO agenda = new AgendaDTO();

			provaRealizada.setProvaId(Integer.parseInt(req
					.getParameter("provaId")));
			provaRealizada.setTituloProva(req.getParameter("tituloProva"));

			agenda.setAgendaId(Integer.parseInt(req.getParameter("agendaId")));

			provaRealizada.setAgenda(agenda);

			provaRealizada.setDataHoraInicio(new Date());
			provaRealizada.setDataHoraFim(new Date(provaRealizada
					.getDataHoraInicio().getTime()
					+ SGPOConstants.TEMPO_DE_PROVA));

			List<QuestaoDTO> listaQuestoes = questoesService
					.listarQuestoesPorProvaId(provaRealizada.getProvaId());

			provaRealizada.setQuantidadeQuestoes(listaQuestoes.size());

			provaRealizada.setProvaRealizadaId(provasService
					.realizarProva(provaRealizada));

			req.setAttribute("provaRealizada", provaRealizada);
			req.setAttribute("msgTempoProva", "Dura��o da prova: "
					+ (SGPOConstants.TEMPO_DE_PROVA / 1000 / 60 / 60));
			req.setAttribute("listaQuestoes", listaQuestoes);
			req.getRequestDispatcher("/secure/provaRealizar.jsp").forward(req,
					resp);

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
