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

import br.com.sgpo.dto.EquipeDTO;
import br.com.sgpo.service.FuncionarioService;
import br.com.sgpo.service.FuncionarioServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/listarEquipes")
public class EquipesListarController extends HttpServlet {

	private static final long serialVersionUID = 1803345723538484813L;
	private static final Logger LOG = Logger
			.getLogger(EquipesListarController.class);

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
					+ "/secure/listarEquipes - method GET");

			if (req.getParameter("pagina") != null) {
				pagina = Integer.parseInt(req.getParameter("pagina"));
			}
			List<EquipeDTO> listaEquipes = listarEquipes(
					(pagina - 1) * registroPorPagina, registroPorPagina);

			numeroRegistros = getTotalRegistros();

			if (numeroRegistros == 0) {
				req.setAttribute("listSize", 0);
				numeroRegistros = 1;
			}

			numeroDePaginas = (int) Math.ceil(numeroRegistros * 1.0
					/ registroPorPagina);

			req.setAttribute("listaEquipes", listaEquipes);

			req.setAttribute("pagina", pagina);
			req.setAttribute("numeroDePaginas", numeroDePaginas);

			req.getRequestDispatcher("/secure/equipesListar.jsp").forward(req,
					resp);

			// Devido ao redirecionamento de outra página para esta,
			// após apresentar a mensagem de confirmação o mesmo é removido.
			req.getSession(true).removeAttribute("msgType");
			req.getSession(true).removeAttribute("msg");

		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado.", e);
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		} catch (SQLException e) {
			LOG.error("Erro em alguma instrução SQL.", e);
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		}
	}

	private List<EquipeDTO> listarEquipes(Integer offSet,
			Integer recordPerPage) throws ClassNotFoundException, SQLException {
		List<EquipeDTO> listaEquipes = new ArrayList<EquipeDTO>();
		listaEquipes = funcionarioService.listarEquipes(offSet,
				recordPerPage);

		return listaEquipes;
	}

	private Integer getTotalRegistros() throws ClassNotFoundException,
			SQLException {

		return funcionarioService.getTotalRegistrosEquipes();
	}
}
