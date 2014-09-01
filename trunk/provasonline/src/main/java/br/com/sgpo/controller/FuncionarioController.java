package br.com.sgpo.controller;

import java.io.IOException;
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

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/funcionario")
public class FuncionarioController extends HttpServlet {

	private static final long serialVersionUID = 1803345723538484813L;
	private static final Logger LOG = Logger
			.getLogger(FuncionarioController.class);

	//private FuncionarioService funcionarioService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acesso a URL: " + req.getContextPath()
				+ "/secure/funcionario - method GET");

		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();

		Funcionario func = null;

		func = new Funcionario();
		func.setNome("João Silva");
		func.setEmail("jsilva@teste.com");
		func.setMatricula(1111);
		func.setUsuario("jsilva");
		func.setPerfilId(1);
		func.setDescricao("Administrador");
		func.setRole("ROLE_ADMIN");

		listaFuncionario.add(func);

		HttpSession session = req.getSession(true);

		session.setAttribute("listaFuncionario", listaFuncionario);

		req.getRequestDispatcher("/secure/funcionario.jsp").forward(req, resp);
		
		//String contextPath = req.getContextPath();
		//resp.sendRedirect(contextPath + "/secure/funcionario.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acesso a URL: " + req.getContextPath()
				+ "/secure/funcionario - method POST");
		req.getRequestDispatcher("/secure/funcionario.jsp").forward(req, resp);
	}

}
