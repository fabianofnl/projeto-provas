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

import org.apache.log4j.Logger;

/**
 * @author Roseli
 * 
 */
@WebFilter(filterName = "loginFilter")
public class LoginFilter implements Filter {

	private FilterConfig filterConfig;
	private static final Logger LOG = Logger.getLogger(LoginFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	/**
	 * Método implementado de @see Filter, realiza filtragem de toda e qualquer
	 * requisição no sistema.
	 * 
	 * Algumas regras de autenticação e autorização forma implementadas aqui.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		LOG.info("Chamou LoginFilter class. Context: "
				+ filterConfig.getServletContext().getContextPath());

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String pathURI = req.getRequestURI();
		String contextPath = req.getContextPath();

		LOG.info("URI: " + pathURI + ", PATH: " + contextPath);
		LOG.info("Status: " + res.getStatus());

		if (pathURI.endsWith("login.jsp") || pathURI.endsWith("/")) {
			res.sendRedirect(contextPath + "/logon.jsp");
		}else{
			chain.doFilter(request, response);
		}

		// REGRAS DE AUTENTICAÇÃO DE AUTORIZAÇÃO ESTÃO ABAIXO

		/*
		if (req.getSession().getAttribute(SGPOConstants.LOGGED_USER) == null) {
			LOG.info("Atributo usuario está NULO");
			res.sendRedirect(contextPath + "/logon.jsp");
		}
		*/
	}

	@Override
	public void destroy() {

	}

}
