package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@WebServlet(value = "/secure/agendarProvas")
public class ProvasAgendarController extends HttpServlet {

	private static final long serialVersionUID = -4294843051118528464L;
	private static final Logger LOG = Logger
			.getLogger(ProvasAgendarController.class);

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
			LOG.info("Acessando classe agendar provas - método GET");

			Integer pagina = 1;
			Integer registroPorPagina = 15;
			Integer numeroRegistros = 0;
			Integer numeroDePaginas = 0;

			if (req.getParameter("pagina") != null) {
				pagina = Integer.parseInt(req.getParameter("pagina"));
			}

			List<FuncionarioDTO> listaFuncionarios = funcionarioService
					.listarColaboradores();
			List<ProvaDTO> listaProvas = provasService.listarProvas();
			List<AgendaDTO> listaAgendas = provasService.listarAgenda(
					(pagina - 1) * registroPorPagina, registroPorPagina);

			numeroRegistros = getTotalRegistrosProvas();

			if (numeroRegistros == 0) {
				req.setAttribute("listSize", 0);
				numeroRegistros = 1;
			}

			numeroDePaginas = (int) Math.ceil(numeroRegistros * 1.0
					/ registroPorPagina);

			req.setAttribute("pagina", pagina);
			req.setAttribute("numeroDePaginas", numeroDePaginas);
			req.setAttribute("listaColaboradores", listaFuncionarios);
			req.setAttribute("listaProvas", listaProvas);
			req.setAttribute("listaAgendas", listaAgendas);

			req.getRequestDispatcher("/secure/agendarProvas.jsp").forward(req,
					resp);

			// Devido ao redirecionamento de outra página para esta,
			// após apresentar a mensagem de confirmação o mesmo é removido.
			req.getSession(true).removeAttribute("msgType");
			req.getSession(true).removeAttribute("msg");

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
			LOG.info("Acessando classe agendar provas - método POST");

			FuncionarioDTO funcionario = new FuncionarioDTO();
			ProvaDTO prova = new ProvaDTO();

			funcionario = funcionarioService
					.buscarFuncionarioPorMatricula(Integer.parseInt(req
							.getParameter("matricula")));
			prova = provasService.buscarProvaPorId(Integer.parseInt(req
					.getParameter("provaId")));

			Date dataAgendada = new SimpleDateFormat("dd/MM/yyyy").parse(req
					.getParameter("data"));

			String context = req.getScheme() + "://" + req.getServerName()
					+ ":" + req.getServerPort() + req.getContextPath();

			boolean exists = provasService.existeProvaAgendadaMesmaData(
					funcionario.getMatricula(), dataAgendada);

			if (!exists) {
				provasService.agendarProva(funcionario, prova, dataAgendada,
						context);

				req.setAttribute("msg", "Prova agendada com sucesso!");
				req.setAttribute("msgType", "info");
			} else {
				req.setAttribute("msg",
						"Este colaborador já possui uma prova agendada nesta data!");
				req.setAttribute("msgType", "warn");
			}

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
		} catch (ParseException e) {
			LOG.error("Erro no formato de data.", e);
			req.setAttribute("msgType", "error");
			req.setAttribute("msg", "Formato da data é inválida!");
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		}
	}

	private Integer getTotalRegistrosProvas() throws ClassNotFoundException,
			SQLException {
		return provasService.getTotalRegistrosAgenda();
	}
}
