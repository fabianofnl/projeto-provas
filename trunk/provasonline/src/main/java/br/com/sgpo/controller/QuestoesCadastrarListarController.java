package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.TemasDTO;
import br.com.sgpo.service.QuestoesService;
import br.com.sgpo.service.QuestoesServiceImpl;
import br.com.sgpo.service.TemasService;
import br.com.sgpo.service.TemasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/questoes")
public class QuestoesCadastrarListarController extends HttpServlet {

	private static final long serialVersionUID = -4294843051118528464L;
	private static final Logger LOG = Logger
			.getLogger(QuestoesCadastrarListarController.class);

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

			LOG.info("Acessando classe cadastrar questões - método GET");

			Integer pagina = 1;
			Integer registroPorPagina = 15;
			Integer numeroRegistros = 0;
			Integer numeroDePaginas = 0;

			LOG.info("Acesso a URL: " + req.getContextPath()
					+ "/secure/funcionario - method GET");

			if (req.getParameter("pagina") != null) {
				pagina = Integer.parseInt(req.getParameter("pagina"));
			}

			List<QuestaoDTO> listaQuestoes = questoesService.listarQuestoes(
					(pagina - 1) * registroPorPagina, registroPorPagina);
			List<TemasDTO> listaTemas = temasService.listarTemas();

			numeroRegistros = getTotalRegistrosQuestoes();

			if (numeroRegistros == 0) {
				req.setAttribute("listSize", 0);
				numeroRegistros = 1;
			}

			numeroDePaginas = (int) Math.ceil(numeroRegistros * 1.0
					/ registroPorPagina);

			req.setAttribute("listaQuestoes", listaQuestoes);
			req.setAttribute("listaTemas", listaTemas);

			req.setAttribute("pagina", pagina);
			req.setAttribute("numeroDePaginas", numeroDePaginas);

			req.getRequestDispatcher("/secure/questoesCadastrarListar.jsp")
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
			LOG.info("Acessando classe cadastrar questões - método GET");

			QuestaoDTO questaoDTO = new QuestaoDTO();

			questaoDTO.setTemaId(Integer.parseInt(req.getParameter("temaId")));
			questaoDTO.setTituloQuestao(req.getParameter("titulo"));
			questaoDTO.setDescricaoQuestao(req.getParameter("descricao"));

			questoesService.gravar(questaoDTO);

			req.setAttribute("msg", "Questão cadastrado com sucesso!");
			req.setAttribute("msgType", "info");

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
		}

	}

	private Integer getTotalRegistrosQuestoes() throws ClassNotFoundException,
			SQLException {
		return questoesService.getTotalRegistrosQuestoes();
	}
}
