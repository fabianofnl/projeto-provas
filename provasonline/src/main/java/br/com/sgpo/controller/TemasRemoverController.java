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

import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.service.TemasService;
import br.com.sgpo.service.TemasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/removerTema")
public class TemasRemoverController extends HttpServlet {

	private static final long serialVersionUID = 4474824008931216299L;

	private static final Logger LOG = Logger
			.getLogger(TemasRemoverController.class);

	private TemasService temasService;

	@Override
	public void init() throws ServletException {
		temasService = new TemasServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe remover Tema - GET");

			String temaId = req.getParameter("temaId");

			if (!StringUtils.isNumeric(temaId)) {
				req.setAttribute("msg", "Erro na aplica��o.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			TemaDTO temaDTO = temasService.buscarTemaPorId(Integer
					.parseInt(temaId));

			req.setAttribute("tema", temaDTO);

			req.getRequestDispatcher("/secure/temaRemover.jsp").forward(req,
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe temas - m�todo POST");

			TemaDTO temasDTO = new TemaDTO();

			temasDTO.setTemaId(Integer.parseInt(req.getParameter("temaId")));

			temasService.remover(temasDTO);

			HttpSession session = req.getSession(true);

			session.setAttribute("msg", "Tema removido com sucesso!");
			session.setAttribute("msgType", "info");

			resp.sendRedirect(req.getContextPath() + "/secure/temas");

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