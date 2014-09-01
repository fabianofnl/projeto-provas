package br.com.sgpo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.sgpo.constants.SGPOConstants;
import br.com.sgpo.model.Funcionario;

/**
 * Método que verifica usuário logado ao acessar página HOME.
 * 
 * @author Roseli
 * 
 */
@WebFilter(filterName = "authFilter")
public class AuthFilter implements Filter {

	private FilterConfig filterConfig;
	private static final Logger LOG = Logger.getLogger(AuthFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		LOG.info("Chamou AuthFilter class. Context: "
				+ filterConfig.getServletContext().getContextPath());

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String pathURI = req.getRequestURI();
		String contextPath = req.getContextPath();

		LOG.info("URI: " + pathURI + ", PATH: " + contextPath);
		LOG.info("Status: " + res.getStatus());

		HttpSession session = req.getSession();

		Funcionario funcionario = (Funcionario) session
				.getAttribute(SGPOConstants.LOGGED_FUNCIONARIO);

		if (funcionario == null) {
			LOG.info("Atributo usuario está NULO");
			res.sendRedirect(contextPath + "/logon");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
