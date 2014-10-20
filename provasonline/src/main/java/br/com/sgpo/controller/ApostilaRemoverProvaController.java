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
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/removerProvaApostila")
public class ApostilaRemoverProvaController extends HttpServlet {

	private static final long serialVersionUID = 4474824008931216299L;

	private static final Logger LOG = Logger
			.getLogger(ApostilaRemoverProvaController.class);

	private ProvasService provasService;

	@Override
	public void init() throws ServletException {
		provasService = new ProvasServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe remover prova apostila - GET");

			String apostilaId = req.getParameter("apostilaId");
			String provaId = req.getParameter("provaId");

			if (!StringUtils.isNumeric(apostilaId)
					&& !StringUtils.isNumeric(provaId)) {
				req.setAttribute("msg", "Erro na aplicação.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			ApostilaDTO apostilaDTO = provasService.buscarApostilaPorId(Integer
					.parseInt(apostilaId));
			ProvaDTO provaDTO = provasService.buscarProvaPorId(Integer
					.parseInt(provaId));

			req.setAttribute("apostila", apostilaDTO);
			req.setAttribute("prova", provaDTO);

			req.getRequestDispatcher("/secure/apostilasRemoverProva.jsp")
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
			LOG.info("Acessando classe apostila remover prova - método POST");

			ApostilaDTO apostilaDTO = new ApostilaDTO();
			ProvaDTO provaDTO = new ProvaDTO();

			apostilaDTO.setApostilaId(Integer.parseInt(req
					.getParameter("apostilaId")));
			provaDTO.setProvaId(Integer.parseInt(req.getParameter("provaId")));

			provasService.removerApostilaProva(apostilaDTO, provaDTO);

			HttpSession session = req.getSession(true);

			session.setAttribute("msg", "Prova removida com sucesso!");
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
