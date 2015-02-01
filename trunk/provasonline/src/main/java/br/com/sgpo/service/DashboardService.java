package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.NotaMediaColaboradorDTO;
import br.com.sgpo.dto.NotaMediaEquipesDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.RelatorioDadosGeraisDTO;

/**
 * Interface que possui os m�todos que gerenciam os Dashboards
 * 
 * @author Roseli
 * 
 */
public interface DashboardService {

	/**
	 * M�todo que consulta a lista de provas agendadas para o colaborador
	 * visualizar
	 * 
	 * @param matricula
	 * @return List<AgendaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<AgendaDTO> listarAgendas(Integer matricula)
			throws ClassNotFoundException, SQLException;

	public List<ApostilaDTO> listarApostilas(Integer matricula)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que consulta a lista de provas realizadas pelo colaborador
	 * 
	 * @param matricula
	 * @return List<ProvaRealizadaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<ProvaRealizadaDTO> listarProvasRealizadasPorMatricula(
			Integer matricula) throws ClassNotFoundException, SQLException;

	public ProvaRealizadaDTO consultarMediaEquipe(Integer matricula)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que consulta dados gerais do sistema, como a quantidade de
	 * funcionarios cadastrados, provas criadas e demais informa��es.
	 * 
	 * @return RelatorioDadosGeraisDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public RelatorioDadosGeraisDTO consultarRelatorioDadosGerais()
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que consulta a lista de notas m�dias das equipes
	 * 
	 * @return List<NotaMediaEquipesDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<NotaMediaEquipesDTO> listarNotaMediaEquipes()
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que consulta as quest�es da prova selecionada para a realiza��o da
	 * mesma
	 * 
	 * @param provaSelecionada
	 * @return List<QuestaoDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<QuestaoDTO> listarQuestoesPorProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException;

	public Integer realizarProva(ProvaRealizadaDTO provaRealizadaSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que processa o t�rmino da prova
	 * 
	 * @param provaRealizadaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void entregarProva(ProvaRealizadaDTO provaRealizadaSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que consulta a lista de notas m�dias dos colaboradores
	 * 
	 * @param matricula
	 * @return List<NotaMediaColaboradorDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<NotaMediaColaboradorDTO> listarNotaMediaColaboradores(
			Integer matricula) throws ClassNotFoundException, SQLException;

}
