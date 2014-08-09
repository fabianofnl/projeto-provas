package br.com.sgpo.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * Classe de inicialização do LOG4J
 * 
 * @author Roseli
 * 
 */
@WebListener
public class ContextListener implements ServletContextListener {

	/**
	 * Método de inicializacao do Log4j
	 * 
	 * <p>Ao inicializar a aplicacão esse método é chamado e é carregado toda a 
	 * configuração do log4j a partir do arquivo log4j.xml</p>
	 * 
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String log4jConfigFile = context
				.getInitParameter("log4j-config-location");
		String fullPath = context.getRealPath("") + File.separator
				+ log4jConfigFile;
		DOMConfigurator.configure(fullPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// nada a fazer
	}

}
