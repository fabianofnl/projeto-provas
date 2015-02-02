package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;

/**
 * Interface que possui os métodos que gerenciam a Agenda
 * 
 * @author Roseli
 * 
 */
public interface AgendaService {

	/**
	 * Método que busca pela lista de agendamento realizado
	 * 
	 * @return List<AgendaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<AgendaDTO> listarAgendas() throws ClassNotFoundException,
			SQLException;

	/**
	 * Método que busca pela lista de agendamento atual (as não realizadas)
	 * 
	 * @return List<AgendaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<AgendaDTO> listarAgendasNaoRealizadas()
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que efetiva o agendamento da prova
	 * 
	 * @param agendaNova
	 * @param contextPath
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void agendarProva(AgendaDTO agendaNova, String contextPath)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que excluir um agendamento
	 * 
	 * @param agendaSelecionada
	 * @param contextPath
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void excluirAgenda(AgendaDTO agendaSelecionada, String contextPath)
			throws ClassNotFoundException, SQLException;
}
