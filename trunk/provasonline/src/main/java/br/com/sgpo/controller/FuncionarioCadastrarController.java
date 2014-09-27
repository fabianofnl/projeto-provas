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
@WebServlet(value = "/secure/cadastrarFuncionario")
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

			List<PerfilDTO> listaPerfis = listarPerfis();

			req.setAttribute("listaPerfis", listaPerfis);

			req.getRequestDispatcher("/secure/funcionarioCadastrar.jsp").forward(req,
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
			LOG.info("Acesso a URL: " + req.getContextPath()
					+ "/secure/funcionario - method POST");

			FuncionarioDTO funcionario = new FuncionarioDTO();

			funcionario.setMatricula(Integer.parseInt(req
					.getParameter("matricula")));
			funcionario.setNome(req.getParameter("nome"));
			funcionario.setFuncao(req.getParameter("funcao"));
			funcionario
					.setPerfilId(Integer.parseInt(req.getParameter("perfil")));
			funcionario.setEmail(req.getParameter("email"));
			funcionario.setUsuario(req.getParameter("user"));
			funcionario.setSenha(req.getParameter("pass"));

			funcionarioService.gravar(funcionario);

			// TODO Verificar um jeito de ajustar a renderização da página
			/*
			 * Após inserir um funcionario, se pressionar CTRL + R ele executa o
			 * form novamente, no caso é como se estive preenchendo o formulario
			 * com os mesmos dados anterior
			 */

			// List<Funcionario> listaFuncionario = listarFuncionarios();
			// List<Perfil> listaPerfis = listarPerfis();

			// req.setAttribute("listaFuncionario", listaFuncionario);
			// req.setAttribute("listaPerfis", listaPerfis);
			req.setAttribute("msgType", "info");
			req.setAttribute("msg", "Funcionario gravado com sucesso!");

			// req.getRequestDispatcher("/secure/funcionario.jsp").forward(req,
			// resp);

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

	private List<PerfilDTO> listarPerfis() throws ClassNotFoundException,
			SQLException {
		List<PerfilDTO> listaPerfis = new ArrayList<PerfilDTO>();
		listaPerfis = funcionarioService.listarPerfis();

		return listaPerfis;
	}
}
