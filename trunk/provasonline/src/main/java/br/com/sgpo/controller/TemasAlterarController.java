package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import br.com.sgpo.dto.TemasDTO;
import br.com.sgpo.service.TemasService;
import br.com.sgpo.service.TemasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/alterarTema")
public class TemasAlterarController extends HttpServlet {

	private static final long serialVersionUID = 4474824008931216299L;

	private static final Logger LOG = Logger
			.getLogger(TemasAlterarController.class);

	private TemasService temasService;

	@Override
	public void init() throws ServletException {
		temasService = new TemasServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe alterar Tema - GET");

			String temaId = req.getParameter("temaId");

			if (!StringUtils.isNumeric(temaId)) {
				req.setAttribute("msg", "Erro na aplicação.");
				req.setAttribute("msgType", "error");
				req.getRequestDispatcher("/error/genericError.jsp").forward(
						req, resp);
				return;
			}

			TemasDTO temaDTO = temasService.buscarTemaPorId(Integer
					.parseInt(temaId));

			req.setAttribute("tema", temaDTO);

			req.getRequestDispatcher("/secure/temaAlterar.jsp").forward(req,
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
			LOG.info("Acessando classe temas - método POST");

			TemasDTO temasDTO = new TemasDTO();

			temasDTO.setTemaId(Integer.parseInt(req.getParameter("temaId")));
			temasDTO.setTitulo(req.getParameter("titulo"));
			temasDTO.setDescricao(req.getParameter("descricao"));

			temasService.alterar(temasDTO);

			req.setAttribute("msg", "Tema alterado com sucesso!");
			req.setAttribute("msgType", "info");

			resp.sendRedirect(req.getContextPath() + "/secure/temas");

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
