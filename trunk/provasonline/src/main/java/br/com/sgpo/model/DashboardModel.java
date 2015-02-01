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
 * Interface com métodos de gerenciamento dos Dashboards
 * 
 * @author Roseli
 * 
 */
public interface DashboardModel {

	/**
	 * Método que consulta a lista de provas agendadas para o colaborador
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
	 * Método que consulta a lista de provas realizadas pelo colaborador
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
	 * Método que consulta dados gerais do sistema, como a quantidade de
	 * funcionarios cadastrados, provas criadas e demais informações.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public RelatorioDadosGeraisDTO consultarRelatorioDadosGerais()
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que consulta a lista de gerentes (perfil de gerente)
	 * 
	 * @return List<NotaMediaEquipesDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<NotaMediaEquipesDTO> listarGerentes()
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que consulta a nota média da equipe
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
	 * Método que consulta as questões da prova selecionada para a realização da
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
	 * Método que consulta as opções da questão selecionada
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
	 * Método que inicia a realização da prova, processo quando confirma o
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
	 * Método que processa o término da prova
	 * 
	 * @param provaRealizadaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void entregarProva(ProvaRealizadaDTO provaRealizadaSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que consulta a lista de notas médias dos colaboradores
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
