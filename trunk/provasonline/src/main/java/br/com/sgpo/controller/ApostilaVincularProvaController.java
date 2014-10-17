package br.com.sgpo.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import br.com.sgpo.constants.SGPOConstants;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/vincularApostilas")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 30, // 10MB
maxRequestSize = 1024 * 1024 * 50)
// 50MB
public class ApostilaVincularProvaController extends HttpServlet {

	private static final long serialVersionUID = -4294843051118528464L;
	private static final Logger LOG = Logger
			.getLogger(ApostilaVincularProvaController.class);

	private ProvasService provasService;

	@Override
	public void init() throws ServletException {
		provasService = new ProvasServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {

			LOG.info("Acessando classe vincular apostilas - método GET");

			Integer pagina = 1;
			Integer registroPorPagina = 15;
			Integer numeroRegistros = 0;
			Integer numeroDePaginas = 0;

			if (req.getParameter("pagina") != null) {
				pagina = Integer.parseInt(req.getParameter("pagina"));
			}

			List<ApostilaDTO> listaApostilas = provasService.listarApostilas(
					(pagina - 1) * registroPorPagina, registroPorPagina);

			numeroRegistros = getTotalRegistrosQuestoes();

			if (numeroRegistros == 0) {
				req.setAttribute("listSize", 0);
				numeroRegistros = 1;
			}

			numeroDePaginas = (int) Math.ceil(numeroRegistros * 1.0
					/ registroPorPagina);

			req.setAttribute("listaApostilas", listaApostilas);

			req.setAttribute("pagina", pagina);
			req.setAttribute("numeroDePaginas", numeroDePaginas);

			req.getRequestDispatcher("/secure/apostilasVincular.jsp").forward(
					req, resp);

			// Devido ao redirecionamento de outra página para esta,
			// após apresentar a mensagem de confirmação o mesmo é removido.
			req.getSession(true).removeAttribute("msgType");
			req.getSession(true).removeAttribute("msg");

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

			LOG.info("Acessando classe vincular apostilas - método POST");

			Boolean flag = false;
			String fullPath = SGPOConstants.SERVER_PATH + File.separator;

			LOG.info("Caminho diretório: " + fullPath);

			File fileSaveDir = new File(fullPath);
			if (!fileSaveDir.exists()) {
				fileSaveDir.mkdir();
			}

			// Essa lógica permite multiplos arquivos, porém, será permitido
			// apenas um
			for (Part part : req.getParts()) {
				String fileName = extractFileName(part);
				String hash = "a" + gerarHash();
				part.write(SGPOConstants.SERVER_PATH + File.separator + hash + "_" + fileName);

				String[] fileNameSplit = fileName.split("\\.");
				String extensao = null;
				if (fileNameSplit.length > 1) {
					extensao = fileNameSplit[fileNameSplit.length - 1];
				}

				ApostilaDTO apostila = new ApostilaDTO();
				apostila.setHashName(hash);
				apostila.setNome(fileName);
				apostila.setServerPath(fullPath);
				apostila.setExtensao(extensao);

				LOG.info("Dados do arquivo: " + apostila.toString());
				provasService.gravarApostila(apostila);

				flag = true;
			}

			if (flag) {
				req.setAttribute("msg", "Arquivo carregado com sucesso!");
				req.setAttribute("msgType", "info");

			} else {
				req.setAttribute("msg", "Falha ao carregar o(s) arquivo(s)!");
				req.setAttribute("msgType", "error");
			}
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
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Erro na geração do HASH.", e);
			req.setAttribute("msgType", "error");
			req.setAttribute("msg", "Erro durante o processamento!");
			req.getRequestDispatcher("/error/error500.jsp").forward(req, resp);
		}
	}

	private Integer getTotalRegistrosQuestoes() throws ClassNotFoundException,
			SQLException {
		return provasService.getTotalRegistrosApostilas();
	}

	/**
	 * Extrai nome do arquivo do cabeçalho do HTTP - content-disposition
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	private String gerarHash() throws NoSuchAlgorithmException,
			UnsupportedEncodingException {

		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 13);
	}
}
