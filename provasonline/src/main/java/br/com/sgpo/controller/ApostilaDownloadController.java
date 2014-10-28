package br.com.sgpo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.sgpo.constants.SGPOConstants;

/**
 * @author Roseli
 * 
 */
@WebServlet(value = "/secure/downloadApostila")
public class ApostilaDownloadController extends HttpServlet {

	private static final long serialVersionUID = 969337022828678594L;

	private static final Logger LOG = Logger
			.getLogger(ApostilaDownloadController.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		LOG.info("Executando classe ApostilaDownload");

		String fullPath = SGPOConstants.SERVER_PATH + File.separator;
		String fileName = req.getParameter("fileName");

		if (fileName == null || "".equals(fileName)) {
			LOG.error("Nome do arquivo não pode ser nulo ou vazio.");
			req.setAttribute("msg",
					"Nome do arquivo não pode ser nulo ou vazio.");
			req.setAttribute("msgType", "error");
			req.getRequestDispatcher("/error/genericError.jsp").forward(req,
					resp);
		}

		File file = new File(fullPath + fileName);

		if (!file.exists()) {
			LOG.error("Arquivo inexistente.");
			req.setAttribute("msg", "Arquivo inexistente.");
			req.setAttribute("msgType", "error");
			req.getRequestDispatcher("/error/genericError.jsp").forward(req,
					resp);
		}

		LOG.info("Local do arquivo no servidor " + file.getAbsolutePath());

		ServletContext ctx = getServletContext();

		InputStream fis = new FileInputStream(file);

		String mimeType = ctx.getMimeType(file.getAbsolutePath());

		resp.setContentType(mimeType != null ? mimeType
				: "application/octet-stream");

		resp.setContentLength((int) file.length());

		resp.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName.substring(15) + "\"");

		ServletOutputStream os = resp.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
	}
}
