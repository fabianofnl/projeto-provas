package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;
import br.com.sgpo.service.FuncionarioService;
import br.com.sgpo.service.FuncionarioServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/funcionario")
public class FuncionarioListarController extends HttpServlet {

	private static final long serialVersionUID = 1803345723538484813L;
	private static final Logger LOG = Logger
			.getLogger(FuncionarioListarController.class);

	private FuncionarioService funcionarioService;

	@Override
	public void init() throws ServletException {
		funcionarioService = new FuncionarioServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			Integer pagina = 1;
			Integer registroPorPagina = 15;
			Integer numeroRegistros = 0;
			Integer numeroDePaginas = 0;

			LOG.info("Acesso a URL: " + req.getContextPath()
					+ "/secure/funcionario - method GET");

			if (req.getParameter("pagina") != null) {
				pagina = Integer.parseInt(req.getParameter("pagina"));
			}
			List<FuncionarioDTO> listaFuncionario = listarFuncionarios(
					(pagina - 1) * registroPorPagina, registroPorPagina);
			List<PerfilDTO> listaPerfis = listarPerfis();

			numeroRegistros = getTotalRegistrosFuncionarios();

			if (numeroRegistros == 0) {
				req.setAttribute("listSize", 0);
				numeroRegistros = 1;
			}

			numeroDePaginas = (int) Math.ceil(numeroRegistros * 1.0
					/ registroPorPagina);

			req.setAttribute("listaFuncionario", listaFuncionario);
			req.setAttribute("listaPerfis", listaPerfis);

			req.setAttribute("pagina", pagina);
			req.setAttribute("numeroDePaginas", numeroDePaginas);

			req.getRequestDispatcher("/secure/funcionario.jsp").forward(req,
					resp);

			// Devido ao redirecionamento de outra p�gina para esta,
			// ap�s apresentar a mensagem de confirma��o o mesmo � removido.
			req.getSession(true).removeAttribute("msgType");
			req.getSession(true).removeAttribute("msg");

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

	private List<FuncionarioDTO> listarFuncionarios(Integer offSet,
			Integer recordPerPage) throws ClassNotFoundException, SQLException {
		List<FuncionarioDTO> listaFuncionario = new ArrayList<FuncionarioDTO>();
		listaFuncionario = funcionarioService.listarFuncionarios(offSet,
				recordPerPage);

		return listaFuncionario;
	}

	private List<PerfilDTO> listarPerfis() throws ClassNotFoundException,
			SQLException {
		List<PerfilDTO> listaPerfis = new ArrayList<PerfilDTO>();
		listaPerfis = funcionarioService.listarPerfis();

		return listaPerfis;
	}

	private Integer getTotalRegistrosFuncionarios() throws ClassNotFoundException,
			SQLException {

		return funcionarioService.getTotalRegistrosFuncionarios();
	}
}