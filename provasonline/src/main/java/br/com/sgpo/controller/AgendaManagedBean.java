package br.com.sgpo.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.service.AgendaService;
import br.com.sgpo.service.AgendaServiceImpl;
import br.com.sgpo.service.FuncionarioService;
import br.com.sgpo.service.FuncionarioServiceImpl;
import br.com.sgpo.service.ProvasService;
import br.com.sgpo.service.ProvasServiceImpl;

/**
 * Classe com a responsabilidade de gerenciar o agendamento de provas do
 * sistema.
 * 
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class AgendaManagedBean implements Serializable {

	private static final long serialVersionUID = 2912376945350346068L;

	private static final Logger LOG = Logger.getLogger(AgendaManagedBean.class);

	private List<AgendaDTO> listaAgendas = new ArrayList<AgendaDTO>();
	private List<AgendaDTO> listaAgendasNaoRealizadas = new ArrayList<AgendaDTO>();

	private List<ProvaDTO> listaProvas = new ArrayList<ProvaDTO>();
	private List<FuncionarioDTO> listaColaboradores = new ArrayList<FuncionarioDTO>();

	private AgendaDTO agendaNova = new AgendaDTO();
	private AgendaDTO agendaSelecionada = new AgendaDTO();

	/**
	 * Método que carrega duas listas de agendas, sendo uma delas a agenda todas
	 * as provas agendadas e a outra apenas as provas não realizadas.
	 * 
	 * @param event
	 */
	public void carregarTabela(ActionEvent event) {

		try {

			LOG.info("Chamando método carregarTabela");

			AgendaService agendaService = new AgendaServiceImpl();
			listaAgendas = agendaService.listarAgendas();
			listaAgendasNaoRealizadas = agendaService
					.listarAgendasNaoRealizadas();

			LOG.info(String.format("Tamanho das listas - 1) [%d], 2) [%d]",
					listaAgendas.size(), listaAgendasNaoRealizadas.size()));
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	/**
	 * Método que popula dois campos de seleção (select) para que possa agendar
	 * a prova
	 * 
	 * @param event
	 */
	public void carregarProvasColaboradores(ActionEvent event) {

		try {

			LOG.info("Chamando método carregarProvasColaboradores");

			FuncionarioService funcionarioService = new FuncionarioServiceImpl();
			ProvasService provasService = new ProvasServiceImpl();
			listaColaboradores = funcionarioService.listarColaboradores();
			listaProvas = provasService.listarProvas();
		} catch (ClassNotFoundException e) {
			LOG.error("Driver do banco de dados não encontrado", e);
		} catch (SQLException e) {
			LOG.error("Houve um problema na query do banco de dados", e);
		}
	}

	/**
	 * Método que efetiva o agendamento da prova
	 * 
	 * @param event
	 */
	public void agendarProva(ActionEvent event) {

		try {

			LOG.info("Chamando método agendar prova");

			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();

			String contextPath = request.getRequestURL().toString();
			contextPath = contextPath.substring(0, contextPath.length()
					- request.getRequestURI().length())
					+ request.getContextPath();

			LOG.info("Context: " + contextPath);

			AgendaService agendaService = new AgendaServiceImpl();
			agendaService.agendarProva(agendaNova, contextPath);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Agendamento realizado com sucesso."));
			limparSessao();
			carregarTabela(event);
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

	/**
	 * Método que excluir um agendamento
	 * 
	 * @param event
	 */
	public void excluirAgendamento(ActionEvent event) {

		try {

			LOG.info("Chamando método excluir agendamento");

			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();

			String contextPath = request.getRequestURL().toString();
			contextPath = contextPath.substring(0, contextPath.length()
					- request.getRequestURI().length())
					+ request.getContextPath();

			LOG.info("Context: " + contextPath);

			AgendaService agendaService = new AgendaServiceImpl();
			agendaService.excluirAgenda(agendaSelecionada, contextPath);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
							"Agendamento excluído com sucesso."));
			limparSessao();
			carregarTabela(event);
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

	/**
	 * Métodos que limpa a sessão após o agendamento.
	 */
	public void limparSessao() {
		agendaNova = new AgendaDTO();
		agendaSelecionada = new AgendaDTO();
	}

	public List<AgendaDTO> getListaAgendas() {
		return listaAgendas;
	}

	public void setListaAgendas(List<AgendaDTO> listaAgendas) {
		this.listaAgendas = listaAgendas;
	}

	public List<AgendaDTO> getListaAgendasNaoRealizadas() {
		return listaAgendasNaoRealizadas;
	}

	public void setListaAgendasNaoRealizadas(
			List<AgendaDTO> listaAgendasNaoRealizadas) {
		this.listaAgendasNaoRealizadas = listaAgendasNaoRealizadas;
	}

	public List<ProvaDTO> getListaProvas() {
		return listaProvas;
	}

	public void setListaProvas(List<ProvaDTO> listaProvas) {
		this.listaProvas = listaProvas;
	}

	public List<FuncionarioDTO> getListaColaboradores() {
		return listaColaboradores;
	}

	public void setListaColaboradores(List<FuncionarioDTO> listaColaboradores) {
		this.listaColaboradores = listaColaboradores;
	}

	public AgendaDTO getAgendaNova() {
		return agendaNova;
	}

	public void setAgendaNova(AgendaDTO agendaNova) {
		this.agendaNova = agendaNova;
	}

	public AgendaDTO getAgendaSelecionada() {
		return agendaSelecionada;
	}

	public void setAgendaSelecionada(AgendaDTO agendaSelecionada) {
		this.agendaSelecionada = agendaSelecionada;
	}

}
