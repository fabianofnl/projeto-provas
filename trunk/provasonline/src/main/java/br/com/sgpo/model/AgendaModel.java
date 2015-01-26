package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;

/**
 * @author Roseli
 * 
 */
public interface AgendaModel {

	public List<AgendaDTO> listarAgendas() throws ClassNotFoundException,
			SQLException;

	public List<AgendaDTO> listarAgendasNaoRealizadas()
			throws ClassNotFoundException, SQLException;

	public void agendarProva(AgendaDTO agendaNova)
			throws ClassNotFoundException, SQLException;

	public void excluirProva(AgendaDTO agendaSelecionada)
			throws ClassNotFoundException, SQLException;

}
