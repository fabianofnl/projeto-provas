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

import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/adicionarProvasApostila")
public class ApostilaAdicionarProvasController extends HttpServlet {

	private static final long serialVersionUID = -1433251825434458033L;
	private static final Logger LOG = Logger
			.getLogger(ApostilaAdicionarProvasController.class);

	private ProvasService provasService;

	@Override
	public void init() throws ServletException {
		provasService = new ProvasServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			LOG.info("Acesso a URL: " + req.getContextPath()
					+ "/secure/adicionarProvasApostila - method GET");

			String apostilaId = req.getParameter("apostilaId");

			if (!StringUtils.isNumeric(apostilaId)) {
				req.setAttribute("msg", "Erro na aplicação.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			ApostilaDTO apostila = provasService.buscarApostilaPorId(Integer
					.parseInt(apostilaId));

			List<ProvaDTO> listaProvas = provasService.listarProvasPorApostila(apostila
					.getApostilaId());

			req.setAttribute("apostila", apostila);
			req.setAttribute("listaProvas", listaProvas);

			req.getRequestDispatcher("/secure/apostilaAdicionarProvas.jsp")
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
					+ "/secure/adicionarProvasApostila - method POST");

			String apostilaId = req.getParameter("apostilaId");
			String[] provasIdString = req.getParameterValues("provasId");

			Integer[] provasIdInteger = new Integer[provasIdString.length];

			for (int i = 0; i < provasIdString.length; i++) {
				provasIdInteger[i] = Integer.parseInt(provasIdString[i]);
			}

			provasService.associarApostilaProvas(Integer.parseInt(apostilaId),
					provasIdInteger);

			HttpSession session = req.getSession(true);

			session.setAttribute("msg", "Prova(as) adicionada(s) com sucesso!");
			session.setAttribute("msgType", "info");

			resp.sendRedirect(req.getContextPath()
					+ "/secure/vincularApostilas");

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
