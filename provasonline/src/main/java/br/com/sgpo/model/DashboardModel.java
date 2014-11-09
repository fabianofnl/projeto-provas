package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.NotaMediaEquipesDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.RelatorioDadosGeraisDTO;

public interface DashboardModel {

	public List<AgendaDTO> listarAgendas(Integer matricula)
			throws ClassNotFoundException, SQLException;

	public List<ApostilaDTO> listarApostilas(Integer matricula)
			throws ClassNotFoundException, SQLException;

	public List<ProvaRealizadaDTO> listarProvasRealizadasPorMatricula(
			Integer matricula) throws ClassNotFoundException, SQLException;

	public ProvaRealizadaDTO consultaMediaEquipe(Integer matricula)
			throws ClassNotFoundException, SQLException;

	public RelatorioDadosGeraisDTO consultarRelatorioDadosGerais()
			throws ClassNotFoundException, SQLException;

	public List<NotaMediaEquipesDTO> listarGerentes()
			throws ClassNotFoundException, SQLException;

	public NotaMediaEquipesDTO consultaMediaEquipePorGerente(
			NotaMediaEquipesDTO notaMediaEquipesDTO)
			throws ClassNotFoundException, SQLException;

}
