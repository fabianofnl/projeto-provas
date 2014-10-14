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

import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/removerProva")
public class ProvasRemoverController extends HttpServlet {

	private static final long serialVersionUID = 4474824008931216299L;

	private static final Logger LOG = Logger
			.getLogger(ProvasRemoverController.class);

	private ProvasService provasService;

	@Override
	public void init() throws ServletException {
		provasService = new ProvasServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe remover Prova - GET");

			String provaId = req.getParameter("provaId");

			if (!StringUtils.isNumeric(provaId)) {
				req.setAttribute("msg", "Erro na aplicação.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			ProvaDTO provaDTO = provasService.buscarProvaPorId(Integer
					.parseInt(provaId));

			req.setAttribute("prova", provaDTO);

			req.getRequestDispatcher("/secure/provasRemover.jsp").forward(req,
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
			LOG.info("Acessando classe provas remover - método POST");

			ProvaDTO provaDTO = new ProvaDTO();

			provaDTO.setProvaId(Integer.parseInt(req.getParameter("provaId")));

			provasService.remover(provaDTO);

			HttpSession session = req.getSession(true);

			session.setAttribute("msg", "Prova removido com sucesso!");
			session.setAttribute("msgType", "info");

			resp.sendRedirect(req.getContextPath() + "/secure/provas");

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
