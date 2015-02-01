package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.AgendaDTO;
import br.com.sgpo.dto.NotaMediaColaboradorDTO;
import br.com.sgpo.dto.NotaMediaEquipesDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.ProvaRealizadaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.RelatorioDadosGeraisDTO;

/**
 * Interface com m�todos de gerenciamento dos Dashboards
 * 
 * @author Roseli
 * 
 */
public interface DashboardModel {

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
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que consulta a lista de gerentes (perfil de gerente)
	 * 
	 * @return List<NotaMediaEquipesDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<NotaMediaEquipesDTO> listarGerentes()
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que consulta a nota m�dia da equipe
	 * 
	 * @param notaMediaEquipesDTO
	 * @return NotaMediaEquipesDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public NotaMediaEquipesDTO consultarMediaEquipePorGerente(
			NotaMediaEquipesDTO notaMediaEquipesDTO)
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

	/**
	 * M�todo que consulta as op��es da quest�o selecionada
	 * 
	 * @param provaSelecionada
	 * @return List<QuestaoDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<OpcaoDTO> listarOpcoesPorQuestao(QuestaoDTO questaoDTO)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que inicia a realiza��o da prova, processo quando confirma o
	 * preenchimento da prova
	 * 
	 * @param provaRealizadaSelecionada
	 * @return Integer
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
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
