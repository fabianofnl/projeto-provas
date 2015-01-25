package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.model.AgendaModel;
import br.com.sgpo.model.AgendaModelImpl;

/**
 * @author Roseli
 * 
 */
public class AgendaServiceImpl implements AgendaService {

	private AgendaModel agendaModel = new AgendaModelImpl();

	public List<AgendaDTO> listarAgendas() throws ClassNotFoundException,
			SQLException {
		return agendaModel.listarAgendas();
	}

	public List<AgendaDTO> listarAgendasNaoRealizadas()
			throws ClassNotFoundException, SQLException {
		return agendaModel.listarAgendasNaoRealizadas();
	}

}
