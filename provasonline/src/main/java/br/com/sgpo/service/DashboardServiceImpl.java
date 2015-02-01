package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.NotaMediaColaboradorDTO;
import br.com.sgpo.dto.NotaMediaEquipesDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.RelatorioDadosGeraisDTO;
import br.com.sgpo.model.DashboardModel;
import br.com.sgpo.model.DashboardModelImpl;

/**
 * Classe que implementa os m�todos de gerenciamento dos Dashboards
 * 
 * @author Roseli
 * 
 */
public class DashboardServiceImpl implements DashboardService {

	private DashboardModel dashboardModel = new DashboardModelImpl();

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
	public ProvaRealizadaDTO consultarMediaEquipe(Integer matricula)
			throws ClassNotFoundException, SQLException {
		return dashboardModel.consultarMediaEquipe(matricula);
	}

	/**
	 * M�todo que consulta dados gerais do sistema, como a quantidade de
	 * funcionarios cadastrados, provas criadas e demais informa��es.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public RelatorioDadosGeraisDTO consultarRelatorioDadosGerais()
			throws ClassNotFoundException, SQLException {
		return dashboardModel.consultarRelatorioDadosGerais();
	}

	/**
	 * M�todo que consulta a lista de notas m�dias das equipes
	 * 
	 * @return List<NotaMediaEquipesDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<NotaMediaEquipesDTO> listarNotaMediaEquipes()
			throws ClassNotFoundException, SQLException {

		List<NotaMediaEquipesDTO> listaMediaEquipes = dashboardModel
				.listarGerentes();

		for (NotaMediaEquipesDTO notaMediaEquipesDTO : listaMediaEquipes) {
			notaMediaEquipesDTO = dashboardModel
					.consultarMediaEquipePorGerente(notaMediaEquipesDTO);
		}

		return listaMediaEquipes;
	}

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
			throws ClassNotFoundException, SQLException {

		List<QuestaoDTO> listaQuestoes = dashboardModel
				.listarQuestoesPorProva(provaSelecionada);
		List<OpcaoDTO> listaOpcoes;

		for (QuestaoDTO questaoDTO : listaQuestoes) {
			listaOpcoes = new ArrayList<OpcaoDTO>();
			listaOpcoes = dashboardModel.listarOpcoesPorQuestao(questaoDTO);
			questaoDTO.setListaOpcoes(listaOpcoes);
		}

		return listaQuestoes;
	}

	@Override
	public Integer realizarProva(ProvaRealizadaDTO provaRealizadaSelecionada)
			throws ClassNotFoundException, SQLException {
		return dashboardModel.realizarProva(provaRealizadaSelecionada);
	}

	/**
	 * M�todo que processa o t�rmino da prova
	 * 
	 * @param provaRealizadaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void entregarProva(ProvaRealizadaDTO provaRealizadaSelecionada)
			throws ClassNotFoundException, SQLException {
		dashboardModel.entregarProva(provaRealizadaSelecionada);
	}

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
			Integer matricula) throws ClassNotFoundException, SQLException {
		return dashboardModel.listarNotaMediaColaboradores(matricula);
	}

}
