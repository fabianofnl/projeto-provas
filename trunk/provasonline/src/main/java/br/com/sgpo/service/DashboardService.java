package br.com.sgpo.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;

public interface DashboardService {

	public List<AgendaDTO> listarAgendas(Integer matricula)
			throws ClassNotFoundException, SQLException;

	public List<ApostilaDTO> listarApostilas(Integer matricula)
			throws ClassNotFoundException, SQLException;

	public List<ProvaRealizadaDTO> listarProvasRealizadasPorMatricula(
			Integer matricula) throws ClassNotFoundException, SQLException;

	public BigDecimal consultarMediaEquipe(Integer matricula)
			throws ClassNotFoundException, SQLException;

}
