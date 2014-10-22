package br.com.sgpo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.ProvaDTO;

public class MailServiceImpl extends Thread {

	private static final Logger LOG = Logger.getLogger(MailServiceImpl.class);

	private FuncionarioDTO funcionario;
	private ProvaDTO prova;
	private Date provaAgendada;
	private String contextPath;

	public MailServiceImpl() {

	}

	public MailServiceImpl(FuncionarioDTO funcionario, ProvaDTO prova,
			Date provaAgendada, String contextPath) {
		super();
		this.funcionario = funcionario;
		this.prova = prova;
		this.provaAgendada = provaAgendada;
		this.contextPath = contextPath;
	}

	@Override
	public void run() {

		try {

			LOG.info("Iniciando processo de envio de email");

			JavaMailSenderImpl senderMail = new JavaMailSenderImpl();

			String mailFrom = "seu email";

			senderMail.setHost("smtp.gmail.com");
			senderMail.setPort(587);
			senderMail.setUsername(mailFrom);
			senderMail.setPassword("sua senha");

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			senderMail.setJavaMailProperties(props);

			JavaMailSender sender = senderMail;

			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true,
					"iso-8859-1");

			helper.setFrom(mailFrom);
			helper.setTo(funcionario.getEmail());

			helper.setSubject("Agendamento de Prova");

			helper.setSentDate(new Date());

			StringBuilder sb = new StringBuilder();

			sb.append("<html>");
			sb.append("<body style='font-family: Verdana; color:#6E6E6E;'>");

			sb.append("<h3>Sistema de Gerenciamento de Provas Online</h3>");

			sb.append("<br>");
			sb.append("Olá").append(" ").append(funcionario.getNome());
			sb.append(",").append(" ");
			sb.append("<br>");
			sb.append("você possui uma prova agendada para o dia");
			sb.append(" ");
			sb.append(new SimpleDateFormat("dd/MM/yyyy").format(provaAgendada));
			sb.append(".");
			sb.append("<br>");
			sb.append("<br>");
			sb.append(prova.getTitulo());
			sb.append("<br>");
			sb.append("<br>");
			sb.append("Acesse esse").append(" ");
			sb.append("<a href='").append(contextPath + "/secure/home.jsp");
			sb.append("'>link</a>");
			sb.append("<br>");
			sb.append("<br>");
			sb.append("</body>");
			sb.append("</html>");

			helper.setText(sb.toString(), true);
			//sender.send(message);

			LOG.info("Finalizado processo de envio de email");
			LOG.debug("Enviado para: " + funcionario.getEmail());
			LOG.debug("Corpo do email:" + sb.toString());

		} catch (MailException e) {
			LOG.error("ERRO [MailException] - " + toString(), e);
		} catch (MessagingException e) {
			LOG.error("ERRO [MessagingException] - " + toString(), e);
		}

	}

}
