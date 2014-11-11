package br.com.sgpo.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.service.SenhaService;
import br.com.sgpo.service.SenhaServiceImpl;

/**
 * Classe referente a recuperação de senha.
 * 
 * @author Roseli
 * 
 */
@WebServlet(value = "/esqueciSenha")
public class SenhaEsqueciController extends HttpServlet {

	private static final long serialVersionUID = -2114908945534512046L;
	private static final Logger LOG = Logger
			.getLogger(SenhaEsqueciController.class);

	private static final String VAR_TITULO_PAGINA = "tituloPagina";
	private static final String TITULO_PAGINA_ESQUECI_SENHA = "Provas Online | Esqueci Senha";
	private static final String VAR_MESSAGE = "msg";
	private static final String VAR_MESSAGE1 = "msg1";
	private static final String MESSAGE = "Usuário inexistente.";
	private static final String MESSAGE1 = "A nova senha foi enviado para seu e-mail.";
	private static final String VAR_USER = "user";
	private static final String VAR_EMAIL = "email";

	private SenhaService esqueciSenhaService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		esqueciSenhaService = new SenhaServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("Acessando Esqueci minha senha | Method GET");
		req.getRequestDispatcher("esqueciSenha.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			LOG.info("Acesso a URL: " + req.getContextPath() + " - method POST");

			String nomeUsuario = req.getParameter(VAR_USER);
			String email = req.getParameter(VAR_EMAIL);

			LOG.info("Usuario: " + nomeUsuario + " | E-mail: " + email);

			FuncionarioDTO funcionario = esqueciSenhaService.buscarUsuario(
					nomeUsuario, email);

			LOG.info("Objeto usuario: " + funcionario);

			if (funcionario != null) {
				String context = req.getScheme() + "://" + req.getServerName()
						+ ":" + req.getServerPort() + req.getContextPath();

				esqueciSenhaService.alterarSenha(funcionario, context);
				req.setAttribute(VAR_MESSAGE1, MESSAGE1);
			} else {
				req.setAttribute(VAR_MESSAGE, MESSAGE);
				req.setAttribute(VAR_USER, nomeUsuario);
				req.setAttribute(VAR_EMAIL, email);
			}

			req.setAttribute(VAR_TITULO_PAGINA, TITULO_PAGINA_ESQUECI_SENHA);
			req.getRequestDispatcher("esqueciSenha.jsp").forward(req, resp);

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
