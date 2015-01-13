package br.com.sgpo.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;

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

	private StreamedContent fileDownload;

	public void carregarTabela(ActionEvent event) {

	}

	public void carregarTemas(ActionEvent event) {

		LOG.info("Total de Temas: ");

	}

	public void carregarOpcoes(ActionEvent event) {

	}

	public void cadastrarProva(ActionEvent event) {

	}

	public void editarProva(ActionEvent event) {

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

	public void apostilaUpload(FileUploadEvent event) {

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

}
