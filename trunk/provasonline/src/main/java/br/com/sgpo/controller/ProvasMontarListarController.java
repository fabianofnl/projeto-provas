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

import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/provas")
public class ProvasMontarListarController extends HttpServlet {

	private static final long serialVersionUID = -4294843051118528464L;
	private static final Logger LOG = Logger
			.getLogger(ProvasMontarListarController.class);

	private ProvasService provasService;

	@Override
	public void init() throws ServletException {
		provasService = new ProvasServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acessando classe montar provas - método GET");

			Integer pagina = 1;
			Integer registroPorPagina = 15;
			Integer numeroRegistros = 0;
			Integer numeroDePaginas = 0;

			if (req.getParameter("pagina") != null) {
				pagina = Integer.parseInt(req.getParameter("pagina"));
			}

			List<ProvaDTO> listaProvas = provasService.listarProvas(
					(pagina - 1) * registroPorPagina, registroPorPagina);

			numeroRegistros = getTotalRegistrosProvas();

			if (numeroRegistros == 0) {
				req.setAttribute("listSize", 0);
				numeroRegistros = 1;
			}

			numeroDePaginas = (int) Math.ceil(numeroRegistros * 1.0
					/ registroPorPagina);

			req.setAttribute("listaProvas", listaProvas);

			req.setAttribute("pagina", pagina);
			req.setAttribute("numeroDePaginas", numeroDePaginas);

			req.getRequestDispatcher("/secure/montarProvas.jsp").forward(req,
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

			LOG.info("Acessando classe montar provas - método POST");
			ProvaDTO provaDTO = new ProvaDTO();
			provaDTO.setTitulo(req.getParameter("titulo"));

			provasService.gravar(provaDTO);

			req.setAttribute("msg", "Prova adicionado com sucesso!");
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

	private Integer getTotalRegistrosProvas() throws ClassNotFoundException,
			SQLException {
		return provasService.getTotalRegistrosProvas();
	}

}
