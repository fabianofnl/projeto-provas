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

/**
 * @author Roseli
 * 
 */
@WebFilter(filterName = "autorizacaoFilter")
public class AutorizacaoFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String pathURI = req.getRequestURI();
		String contextPath = req.getContextPath();

		System.out.println("URI: " + pathURI + ", PATH: " + contextPath);

		System.out.println("Status: " + res.getStatus());

		if (pathURI.endsWith("login.jsp") || pathURI.endsWith("/")) {
			res.sendRedirect(contextPath + "/logon.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}
