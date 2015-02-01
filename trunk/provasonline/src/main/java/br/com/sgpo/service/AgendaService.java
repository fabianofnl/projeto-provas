package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;

/**
 * @author Roseli
 * 
 */
public interface AgendaService {

	/**
	 * M�todo que busca pela lista de agendamento realizado
	 * 
	 * @return List<AgendaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<AgendaDTO> listarAgendas() throws ClassNotFoundException,
			SQLException;

	/**
	 * M�todo que busca pela lista de agendamento atual (as n�o realizadas)
	 * 
	 * @return List<AgendaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<AgendaDTO> listarAgendasNaoRealizadas()
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que efetiva o agendamento da prova
	 * 
	 * @param agendaNova
	 * @param contextPath
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void agendarProva(AgendaDTO agendaNova, String contextPath)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que excluir um agendamento
	 * 
	 * @param agendaSelecionada
	 * @param contextPath
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void excluirAgenda(AgendaDTO agendaSelecionada, String contextPath)
			throws ClassNotFoundException, SQLException;
}
