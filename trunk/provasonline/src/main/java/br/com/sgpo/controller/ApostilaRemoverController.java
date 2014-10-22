package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/removerApostila")
public class ApostilaRemoverController extends HttpServlet {

	private static final long serialVersionUID = 4474824008931216299L;

	private static final Logger LOG = Logger
			.getLogger(ApostilaRemoverController.class);

	private ProvasService provasService;

	@Override
	public void init() throws ServletException {
		provasService = new ProvasServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe remover Apostila - GET");

			String apostilaId = req.getParameter("apostilaId");

			if (!StringUtils.isNumeric(apostilaId)) {
				req.setAttribute("msg", "Erro na aplica��o.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			ApostilaDTO apostilaDTO = provasService.buscarApostilaPorId(Integer
					.parseInt(apostilaId));

			req.setAttribute("apostila", apostilaDTO);

			req.getRequestDispatcher("/secure/apostilaRemover.jsp").forward(
					req, resp);

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
			LOG.info("Acessando classe apostila remover - m�todo POST");

			ApostilaDTO apostilaDTO = new ApostilaDTO();

			apostilaDTO.setApostilaId(Integer.parseInt(req
					.getParameter("apostilaId")));
			apostilaDTO.setNome(req.getParameter("nome"));
			apostilaDTO.setExtensao(req.getParameter("extensao"));
			apostilaDTO.setHashName(req.getParameter("hashName"));
			apostilaDTO.setServerPath(req.getParameter("serverPath"));

			provasService.removerApostila(apostilaDTO);

			HttpSession session = req.getSession(true);

			session.setAttribute("msg", "Apostila removida com sucesso!");
			session.setAttribute("msgType", "info");

			resp.sendRedirect(req.getContextPath()
					+ "/secure/vincularApostilas");

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