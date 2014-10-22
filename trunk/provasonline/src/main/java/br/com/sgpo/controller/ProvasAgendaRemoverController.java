package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.service.FuncionarioService;
import br.com.sgpo.service.FuncionarioServiceImpl;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/cancelarAgenda")
public class ProvasAgendaRemoverController extends HttpServlet {

	private static final long serialVersionUID = 4474824008931216299L;

	private static final Logger LOG = Logger
			.getLogger(ProvasAgendaRemoverController.class);

	private ProvasService provasService;
	private FuncionarioService funcionarioService;

	@Override
	public void init() throws ServletException {
		provasService = new ProvasServiceImpl();
		funcionarioService = new FuncionarioServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe cancelar Agenda - GET");

			String agendaId = req.getParameter("agendaId");

			if (!StringUtils.isNumeric(agendaId)) {
				req.setAttribute("msg", "Erro na aplicação.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			AgendaDTO agendaDTO = provasService.buscarAgendaPorId(Integer
					.parseInt(agendaId));

			req.setAttribute("agenda", agendaDTO);

			req.getRequestDispatcher("/secure/agendaRemover.jsp").forward(req,
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
			LOG.info("Acessando classe provas agenda remover - método POST");

			AgendaDTO agenda = new AgendaDTO();
			ProvaDTO prova = new ProvaDTO();
			FuncionarioDTO funcionario = new FuncionarioDTO();

			String provaId = req.getParameter("provaId");
			String matricula = req.getParameter("matricula");
			String data = req.getParameter("data");

			prova = provasService.buscarProvaPorId(Integer.parseInt(provaId));
			funcionario = funcionarioService
					.buscarFuncionarioPorMatricula(Integer.parseInt(matricula));

			agenda.setAgendaId(Integer.parseInt(req.getParameter("agendaId")));
			agenda.setProva(prova);
			agenda.setFuncionario(funcionario);
			agenda.setProvaAgendada(new SimpleDateFormat("dd/MM/yyyy")
					.parse(data));

			String context = req.getScheme() + "://" + req.getServerName()
					+ ":" + req.getServerPort() + req.getContextPath();

			provasService.removerAgenda(agenda, context);

			HttpSession session = req.getSession(true);

			session.setAttribute("msg", "Agenda removida com sucesso!");
			session.setAttribute("msgType", "info");

			resp.sendRedirect(req.getContextPath() + "/secure/agendarProvas");

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
		} catch (ParseException e) {
			LOG.error("Erro no formato de data.", e);
			req.setAttribute("msgType", "error");
			req.setAttribute("msg", "Formato da data é inválida!");
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		}
	}
}
