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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.OpcaoDTO;
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
@WebServlet(value = "/secure/entregarProva")
public class ProvaEntregarController extends HttpServlet {

	private static final long serialVersionUID = 3599810451461867688L;
	private static final Logger LOG = Logger
			.getLogger(ProvaEntregarController.class);

	private ProvasService provasService;
	private QuestoesService questoesService;

	@Override
	public void init() throws ServletException {
		provasService = new ProvasServiceImpl();
		questoesService = new QuestoesServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			LOG.info("chamando classe Prova entregar Controler");

			ProvaRealizadaDTO provaRealizada = new ProvaRealizadaDTO();
			AgendaDTO agenda = new AgendaDTO();
			int acertos = 0;

			provaRealizada.setDataHoraFinalizado(new Date());

			provaRealizada.setProvaRealizadaId(Integer.parseInt(req
					.getParameter("provaRealizadaId")));
			agenda.setAgendaId(Integer.parseInt(req.getParameter("agendaId")));
			provaRealizada.setAgenda(agenda);
			provaRealizada.setProvaId(Integer.parseInt(req
					.getParameter("provaId")));

			if (provaRealizada.getDataHoraFinalizado().getTime() <= provasService
					.buscarPorDataHoraFim(provaRealizada.getProvaRealizadaId())) {

				List<QuestaoDTO> listaQuestoes = questoesService
						.listarQuestoesPorProvaId(provaRealizada.getProvaId());

				for (QuestaoDTO questaoDTO : listaQuestoes) {

					Integer opcaoSelecionada = Integer
							.parseInt(req.getParameter("radio-"
									+ questaoDTO.getQuestaoId()));

					for (OpcaoDTO opcaoDTO : questaoDTO.getListaOpcoes()) {
						if (opcaoDTO.getFlag()) {
							if (opcaoSelecionada.equals(opcaoDTO.getOpcaoId())) {
								acertos++;
							}
						}
					}
				}

				provaRealizada.setQuantidadeAcertos(acertos);
				provasService.entregarProva(provaRealizada);

				HttpSession session = req.getSession(true);
				session.setAttribute("msgType", "info");
				session.setAttribute("msg", "Prova entregue com sucesso.");
				resp.sendRedirect(req.getContextPath() + "/secure/dashboard");

			} else {
				req.setAttribute("msgType", "warn");
				req.setAttribute("msg",
						"O tempo para a realização da prova terminou, não foi possível finalizar.");
				resp.sendRedirect(req.getContextPath() + "secure/dashboard");
			}

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
