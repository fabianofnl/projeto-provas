package br.com.sgpo.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.model.DashboardModel;
import br.com.sgpo.model.DashboardModelImpl;

public class DashboardServiceImpl implements DashboardService {

	private DashboardModel dashboardModel = new DashboardModelImpl();

	@Override
	public List<AgendaDTO> listarAgendas(Integer matricula)
			throws ClassNotFoundException, SQLException {
		return dashboardModel.listarAgendas(matricula);
	}

	@Override
	public List<ApostilaDTO> listarApostilas(Integer matricula)
			throws ClassNotFoundException, SQLException {
		return dashboardModel.listarApostilas(matricula);
	}

	@Override
	public List<ProvaRealizadaDTO> listarProvasRealizadasPorMatricula(
			Integer matricula) throws ClassNotFoundException, SQLException {
		return dashboardModel.listarProvasRealizadasPorMatricula(matricula);
	}

	@Override
	public BigDecimal consultarMediaEquipe(Integer matricula)
			throws ClassNotFoundException, SQLException {
		return dashboardModel.consultaMediaEquipe(matricula);
	}

}
