package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;

/**
 * Inteface que possui os métodos de gerencimento do Tema
 * 
 * @author Roseli
 * 
 */
public interface AgendaModel {

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
	public void agendarProva(AgendaDTO agendaNova)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que excluir um agendamento
	 * 
	 * @param agendaSelecionada
	 * @param contextPath
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void excluirProva(AgendaDTO agendaSelecionada)
			throws ClassNotFoundException, SQLException;

}
