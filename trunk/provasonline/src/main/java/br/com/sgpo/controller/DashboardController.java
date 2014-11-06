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

import org.apache.log4j.Logger;

import br.com.sgpo.constants.SGPOConstants;
import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.service.DashboardService;
import br.com.sgpo.service.DashboardServiceImpl;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;
import br.com.sgpo.service.QuestoesService;
import br.com.sgpo.service.QuestoesServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/dashboard")
public class DashboardController extends HttpServlet {

	private static final long serialVersionUID = 8805206183250554983L;
	private static final Logger LOG = Logger
			.getLogger(DashboardController.class);

	private DashboardService dashboardService;
	private FuncionarioDTO funcionario;

	@Override
	public void init() throws ServletException {
		dashboardService = new DashboardServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			LOG.info("Chamado classe Dashboard");

			funcionario = (FuncionarioDTO) req.getSession(true).getAttribute(
					"funcionario");

			if (funcionario != null) {

				switch (funcionario.getRole()) {

				case SGPOConstants.LOGGED_ROLE_COLABORADOR:
					processarDadosColaborador(req, resp);
					break;

				case SGPOConstants.LOGGED_ROLE_GERENTE:
					processarDadosGerente(req, resp);
					break;

				case SGPOConstants.LOGGED_ROLE_INSTRUTOR:
					processarDadosInstrutor(req, resp);
					break;

				case SGPOConstants.LOGGED_ROLE_ADMIN:
					processarDadosAdministrador(req, resp);
					break;

				default:
					LOG.warn("Você não possui autorização para acessar");
					req.getRequestDispatcher("/error/errorNoAuthorization.jsp")
							.forward(req, resp);
					break;
				}

			} else {
				LOG.warn("Acesso sem autenticação, possível bug");
				req.getRequestDispatcher("/error/errorNoAuthorization.jsp")
						.forward(req, resp);
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

	private void processarDadosColaborador(HttpServletRequest req,
			HttpServletResponse resp) throws ClassNotFoundException,
			SQLException, ServletException, IOException {

		// verifica se existe prova em andamento.
		ProvasService provasService = new ProvasServiceImpl();

		ProvaRealizadaDTO provaRealizada = provasService
				.buscarProvaRealizadaPorColaboradorMat(funcionario
						.getMatricula());

		if (provaRealizada != null) {

			QuestoesService questoesService = new QuestoesServiceImpl();

			req.setAttribute("provaRealizada", provaRealizada);
			req.setAttribute("msgTempoProva", "Duração da prova: "
					+ (SGPOConstants.TEMPO_DE_PROVA / 1000 / 60 / 60));
			req.setAttribute("quantidadeQuestoes",
					provaRealizada.getQuantidadeQuestoes());
			List<QuestaoDTO> listaQuestoes = questoesService
					.listarQuestoesPorProvaId(provaRealizada.getProvaId());
			req.setAttribute("listaQuestoes", listaQuestoes);
			req.getRequestDispatcher("/secure/provaRealizar.jsp").forward(req,
					resp);

		} else {

			List<AgendaDTO> listaAgendas = dashboardService
					.listarAgendas(funcionario.getMatricula());

			List<ApostilaDTO> listaApostilas = dashboardService
					.listarApostilas(funcionario.getMatricula());

			req.setAttribute("listaAgendas", listaAgendas);
			req.setAttribute("listaApostilas", listaApostilas);

			req.getRequestDispatcher("/secure/dashboard.jsp")
					.forward(req, resp);
		}
		// Limpando mensagens da sessão
		HttpSession session = req.getSession(true);
		session.removeAttribute("msg");
		session.removeAttribute("msgType");
	}

	private void processarDadosGerente(HttpServletRequest req,
			HttpServletResponse resp) throws ClassNotFoundException,
			SQLException, ServletException, IOException {

		req.getRequestDispatcher("/secure/dashboard.jsp").forward(req, resp);
	}

	private void processarDadosInstrutor(HttpServletRequest req,
			HttpServletResponse resp) throws ClassNotFoundException,
			SQLException, ServletException, IOException {

		req.getRequestDispatcher("/secure/dashboard.jsp").forward(req, resp);
	}

	private void processarDadosAdministrador(HttpServletRequest req,
			HttpServletResponse resp) throws ClassNotFoundException,
			SQLException, ServletException, IOException {

		req.getRequestDispatcher("/secure/dashboard.jsp").forward(req, resp);
	}
}
