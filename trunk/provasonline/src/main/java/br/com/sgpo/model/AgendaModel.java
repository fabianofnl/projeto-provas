package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;

/**
 * @author Roseli
 * 
 */
public interface AgendaModel {

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
	public void agendarProva(AgendaDTO agendaNova)
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
	public void excluirProva(AgendaDTO agendaSelecionada)
			throws ClassNotFoundException, SQLException;

}
