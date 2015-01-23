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

import br.com.sgpo.constants.SGPOConstants;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;
import br.com.sgpo.service.TemasService;
import br.com.sgpo.service.TemasServiceImpl;

/**
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class ProvaManagedBean implements Serializable {

	private static final long serialVersionUID = 1115044849242787671L;
	private static final Logger LOG = Logger.getLogger(ProvaManagedBean.class);

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
	private List<TemaDTO> listaTemas = new ArrayList<TemaDTO>();

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

		try {

			TemasService temasService = new TemasServiceImpl();
			listaTemas = temasService.listarTemas();
			LOG.info("Total de Temas: " + listaTemas.size());

		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}

	}

	public void carregarOpcoes() {

		try {
			ProvasService provaService = new ProvasServiceImpl();
			listaOpcoes = provaService
					.listarOpcoesPorQuestaoId(questaoSelecionada.getQuestaoId());
			LOG.info("Total de Opcões: " + listaOpcoes.size());
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public void cadastrarProva(ActionEvent event) {

		try {
			ProvasService provaService = new ProvasServiceImpl();
			provaService.gravar(provaNova);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							provaNova.getTitulo()
									+ " cadastrada(o) com sucesso."));

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
									+ " alterada(o) com sucesso."));

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
		try {
			ProvasService provasService = new ProvasServiceImpl();
			provasService.excluirProva(provaSelecionada);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							provaSelecionada.getTitulo()
									+ " excluída(o) com sucesso."));

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
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Houve um problema na exclusão dde alguma apostila", e);
		}
	}

	public void cadastrarQuestao(ActionEvent event) {

		try {

			ProvasService provasService = new ProvasServiceImpl();
			questaoNova.setProvaId(provaSelecionada.getProvaId());
			provasService.gravarQuestao(questaoNova);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							questaoNova.getTituloQuestao()
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

	public void editarQuestao(ActionEvent event) {

		try {
			ProvasService provasService = new ProvasServiceImpl();
			provasService.alterarQuestao(questaoSelecionada);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							questaoSelecionada.getTituloQuestao()
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

	public void excluirQuestao(ActionEvent event) {

		try {
			ProvasService provasService = new ProvasServiceImpl();
			provasService.excluirQuestao(questaoSelecionada);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							questaoSelecionada.getTituloQuestao()
									+ " excluído(a) com sucesso."));

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

	public void cadastrarOpcao(ActionEvent event) {

		try {
			ProvasService provasService = new ProvasServiceImpl();
			opcaoNova.setQuestaoId(questaoSelecionada.getQuestaoId());
			provasService.gravarOpcao(opcaoNova);

			carregarOpcoes();
			opcaoNova = new OpcaoDTO();
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public void definirOpcao() {

		try {
			ProvasService provasService = new ProvasServiceImpl();
			provasService.definirOpcao(opcaoSelecionada);
			carregarOpcoes();
			opcaoSelecionada = new OpcaoDTO();
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public void excluirOpcao(ActionEvent event) {

		try {
			ProvasService provasService = new ProvasServiceImpl();
			provasService.excluirOpcao(opcaoSelecionada);
			carregarOpcoes();
			opcaoSelecionada = new OpcaoDTO();
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	public void apostilaUpload(ActionEvent event) {

		try {

			if (fileUpload != null) {

				LOG.info("Arquivo: " + fileUpload.getFileName());
				String fileName = fileUpload.getFileName();
				String fullPath = SGPOConstants.SERVER_PATH + File.separator;
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
						SGPOConstants.SERVER_PATH + File.separator + hash + "_"
								+ fileName));

				InputStream is = fileUpload.getInputstream();
				byte[] buffer = new byte[SGPOConstants.BUFFER_SIZE];

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

				limparSessao();

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

		try {
			ProvasService provaService = new ProvasServiceImpl();
			provaService.removerApostila(apostilaSelecionada);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							apostilaSelecionada.getNome()
									+ " excluído(a) com sucesso."));
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
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
							"Houve um erro na aplicação, tente mais tarde"));
			LOG.error("Erro ao apagar arquivo", e);
		}

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

	public List<TemaDTO> getListaTemas() {
		return listaTemas;
	}

	public void setListaTemas(List<TemaDTO> listaTemas) {
		this.listaTemas = listaTemas;
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
