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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.sgpo.model.Funcionario;
import br.com.sgpo.model.Perfil;
import br.com.sgpo.service.FuncionarioService;
import br.com.sgpo.service.FuncionarioServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/funcionario")
public class FuncionarioCadastrarController extends HttpServlet {

	private static final long serialVersionUID = 1803345723538484813L;
	private static final Logger LOG = Logger
			.getLogger(FuncionarioCadastrarController.class);

	private FuncionarioService funcionarioService;
	
	@Override
	public void init() throws ServletException {
		funcionarioService = new FuncionarioServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			LOG.info("Acesso a URL: " + req.getContextPath()
					+ "/secure/funcionario - method GET");

			List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
			List<Perfil> listaPerfis = new ArrayList<Perfil>();

			listaFuncionario = funcionarioService.listarFuncionarios();
			listaPerfis = funcionarioService.listarPerfis();

			//HttpSession session = req.getSession(true);

			req.setAttribute("listaFuncionario", listaFuncionario);
			req.setAttribute("listaPerfis", listaPerfis);
			
			Integer page = 1;
			
			if(req.getParameter("page") != null){
				page = Integer.parseInt(req.getParameter("page"));
			}
			
			//TODO teste
			req.setAttribute("page", 9);
			req.setAttribute("totalPages", 20);

			req.getRequestDispatcher("/secure/funcionario.jsp").forward(req,
					resp);

			// String contextPath = req.getContextPath();
			// resp.sendRedirect(contextPath + "/secure/funcionario.jsp");
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado.", e);
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		} catch (SQLException e) {
			LOG.error("Erro em alguma instrução SQL.", e);
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acesso a URL: " + req.getContextPath()
				+ "/secure/funcionario - method POST");
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setMatricula(Integer.parseInt(req.getParameter("matricula")));
		funcionario.setNome(req.getParameter("nome"));

		LOG.info("Nome: " + funcionario.getNome());

		req.getRequestDispatcher("/secure/funcionario.jsp").forward(req, resp);
	}

}
