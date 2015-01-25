package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;

/**
 * @author Roseli
 * 
 */
public interface AgendaService {

	public List<AgendaDTO> listarAgendas() throws ClassNotFoundException,
			SQLException;

	public List<AgendaDTO> listarAgendasNaoRealizadas()
			throws ClassNotFoundException, SQLException;
}
