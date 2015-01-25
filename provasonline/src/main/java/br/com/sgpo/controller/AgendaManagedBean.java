package br.com.sgpo.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.service.AgendaService;
import br.com.sgpo.service.AgendaServiceImpl;

/**
 * @author Roseli
 * 
 */
@ManagedBean
@ViewScoped
public class AgendaManagedBean implements Serializable {

	private static final long serialVersionUID = -8143523018817793378L;

	private static final Logger LOG = Logger.getLogger(AgendaManagedBean.class);

	List<AgendaDTO> listaAgendas = new ArrayList<AgendaDTO>();
	List<AgendaDTO> listaAgendasNaoRealizadas = new ArrayList<AgendaDTO>();

	/**
	 * Método que carrega duas listas de agendas, sendo uma delas a agenda todas
	 * as provas agendadas e a outra apenas as provas não realizadas.
	 * 
	 * @param event
	 */
	public void carregarTabela(ActionEvent event) {

		try {
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

}
