package br.com.sgpo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;

/**
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class ProvaManagedBean implements Serializable {

	private static final long serialVersionUID = 1115044849242787671L;
	private static final Logger LOG = Logger.getLogger(ProvaManagedBean.class);

	private static final String SERVER_PATH = "D:\\provasonline\\anexos";
	private static final int BUFFER_SIZE = 8192;

	private ProvaDTO provaSelecionada = new ProvaDTO();
	private ProvaDTO provaNova = new ProvaDTO();

	private QuestaoDTO questaoSelecionada = new QuestaoDTO();
	private QuestaoDTO questaoNova = new QuestaoDTO();

	private OpcaoDTO opcaoSelecionada = new OpcaoDTO();
	private OpcaoDTO opcaoNova = new OpcaoDTO();

	private ApostilaDTO apostilaSelecionada = new ApostilaDTO();
	private ApostilaDTO apostilaNova = new ApostilaDTO();

	private List<ProvaDTO> listaProvas = new ArrayList<ProvaDTO>();
	private List<OpcaoDTO> listaOpcoes = new ArrayList<OpcaoDTO>();
	private List<ApostilaDTO> listaApostilas = new ArrayList<ApostilaDTO>();

	private StreamedContent fileDownload;
	private UploadedFile fileUpload;

	public void carregarTabela(ActionEvent event) {

		try {
			ProvasService provaService = new ProvasServiceImpl();
			listaProvas = provaService.listarProvas();
		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	public void carregarTemas(ActionEvent event) {

		LOG.info("Total de Temas: ");

	}

	public void carregarOpcoes(ActionEvent event) {

	}

	public void cadastrarProva(ActionEvent event) {

		try {
			ProvasService provaService = new ProvasServiceImpl();
			provaService.gravar(provaNova);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							provaNova.getTitulo()
									+ " cadastrado(a) com sucesso."));

			carregarTabela(event);
			limparSessao();

		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	public void editarProva(ActionEvent event) {

		try {
			ProvasService provaService = new ProvasServiceImpl();
			provaService.alterarProva(provaSelecionada);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							provaSelecionada.getTitulo()
									+ " alterado(a) com sucesso."));

			carregarTabela(event);
			limparSessao();

		} catch (ClassNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public void excluirProva(ActionEvent event) {

	}

	public void cadastrarQuestao(ActionEvent event) {

	}

	public void editarQuestao(ActionEvent event) {

	}

	public void excluirQuestao(ActionEvent event) {

	}

	public void cadastrarOpcao(ActionEvent event) {

	}

	public void editarOpcao(ActionEvent event) {

	}

	public void excluirOpcao(ActionEvent event) {

	}

	public void apostilaUpload(ActionEvent event) {

		try {

			if (fileUpload != null) {

				LOG.info("Arquivo: " + fileUpload.getFileName());
				String fileName = fileUpload.getFileName();
				String fullPath = SERVER_PATH + File.separator;
				LOG.info("Diretório: " + fullPath);

				File fileSaveDir = new File(fullPath);
				if (!fileSaveDir.exists()) {
					fileSaveDir.mkdir();
				}
				String hash = "a" + gerarHash();

				String[] fileNameSplit = fileName.split("\\.");
				String extensao = null;
				if (fileNameSplit.length > 1) {
					extensao = fileNameSplit[fileNameSplit.length - 1];
				}

				FileOutputStream fos = new FileOutputStream(new File(
						SERVER_PATH + File.separator + hash + "_" + fileName));

				InputStream is = fileUpload.getInputstream();
				byte[] buffer = new byte[BUFFER_SIZE];

				int a;

				while (true) {
					a = is.read(buffer);
					if (a < 0)
						break;
					fos.write(buffer, 0, a);
					fos.flush();
				}

				fos.close();
				is.close();

				ApostilaDTO apostila = new ApostilaDTO();
				apostila.setHashName(hash);
				apostila.setNome(fileName);
				apostila.setServerPath(fullPath);
				apostila.setExtensao(extensao);
				apostila.setProvaId(provaSelecionada.getProvaId());

				ProvasService provaService = new ProvasServiceImpl();
				provaService.gravarApostila(apostila);

			} else {
				LOG.error("Arquivo selecionado para upload está vazio");
			}

		} catch (NoSuchAlgorithmException e) {
			LOG.error("Erro ao gerar hash", e);
		} catch (UnsupportedEncodingException e) {
			LOG.error("Erro ao gerar hash", e);
		} catch (FileNotFoundException e) {
			LOG.error("Erro ao processar arquivo para upload", e);
		} catch (IOException e) {
			LOG.error("Erro ao processar arquivo para upload", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	public void apostilaDownload(ActionEvent event) {

		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/resources/demo/images/optimus.jpg");
		fileDownload = new DefaultStreamedContent(stream, "image/jpg",
				"downloaded_optimus.jpg");

	}

	public void excluirApostila(ActionEvent event) {

	}

	private void limparSessao() {
		provaNova = new ProvaDTO();
		provaSelecionada = new ProvaDTO();
		questaoNova = new QuestaoDTO();
		questaoSelecionada = new QuestaoDTO();
		opcaoNova = new OpcaoDTO();
		opcaoSelecionada = new OpcaoDTO();
		apostilaNova = new ApostilaDTO();
		apostilaSelecionada = new ApostilaDTO();

	}

	private String gerarHash() throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 13);
	}

	public ProvaDTO getProvaNova() {
		return provaNova;
	}

	public void setProvaNova(ProvaDTO provaNova) {
		this.provaNova = provaNova;
	}

	public QuestaoDTO getQuestaoNova() {
		return questaoNova;
	}

	public void setQuestaoNova(QuestaoDTO questaoNova) {
		this.questaoNova = questaoNova;
	}

	public OpcaoDTO getOpcaoNova() {
		return opcaoNova;
	}

	public void setOpcaoNova(OpcaoDTO opcaoNova) {
		this.opcaoNova = opcaoNova;
	}

	public ApostilaDTO getApostilaNova() {
		return apostilaNova;
	}

	public void setApostilaNova(ApostilaDTO apostilaNova) {
		this.apostilaNova = apostilaNova;
	}

	public ProvaDTO getProvaSelecionada() {
		return provaSelecionada;
	}

	public void setProvaSelecionada(ProvaDTO provaSelecionada) {
		this.provaSelecionada = provaSelecionada;
	}

	public QuestaoDTO getQuestaoSelecionada() {
		return questaoSelecionada;
	}

	public void setQuestaoSelecionada(QuestaoDTO questaoSelecionada) {
		this.questaoSelecionada = questaoSelecionada;
	}

	public OpcaoDTO getOpcaoSelecionada() {
		return opcaoSelecionada;
	}

	public void setOpcaoSelecionada(OpcaoDTO opcaoSelecionada) {
		this.opcaoSelecionada = opcaoSelecionada;
	}

	public ApostilaDTO getApostilaSelecionada() {
		return apostilaSelecionada;
	}

	public void setApostilaSelecionada(ApostilaDTO apostilaSelecionada) {
		this.apostilaSelecionada = apostilaSelecionada;
	}

	public List<ProvaDTO> getListaProvas() {
		return listaProvas;
	}

	public void setListaProvas(List<ProvaDTO> listaProvas) {
		this.listaProvas = listaProvas;
	}

	public List<OpcaoDTO> getListaOpcoes() {
		return listaOpcoes;
	}

	public void setListaOpcoes(List<OpcaoDTO> listaOpcoes) {
		this.listaOpcoes = listaOpcoes;
	}

	public List<ApostilaDTO> getListaApostilas() {
		return listaApostilas;
	}

	public void setListaApostilas(List<ApostilaDTO> listaApostilas) {
		this.listaApostilas = listaApostilas;
	}

	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	public UploadedFile getFileUpload() {
		LOG.info("getFileUpload");
		return fileUpload;
	}

	public void setFileUpload(UploadedFile fileUpload) {
		LOG.info("setFileUpload");
		this.fileUpload = fileUpload;
	}

}
