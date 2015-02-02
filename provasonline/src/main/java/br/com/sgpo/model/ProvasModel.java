package br.com.sgpo.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;

/**
 * Interface que possui os métodos de gerenciamento das provas
 * 
 * @author Roseli
 * 
 */
public interface ProvasModel {

	/**
	 * Método que cadastra a prova no sistema
	 * 
	 * @param provaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravar(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException;

	/**
	 * Método que consulta dados da prova por provaId
	 * 
	 * @param provaId
	 * @return ProvaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ProvaDTO buscarProvaPorId(Integer provaId)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método responsável pelo upload dos arquivos (apostilas)
	 * 
	 * @param apostilaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravaApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que remove a apostila
	 * 
	 * @param apostilaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void removerApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que consulta a lista de provas criadas no sistema
	 * 
	 * @return List<ProvaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ProvaDTO> listarProvas() throws ClassNotFoundException,
			SQLException;

	/**
	 * Método que altera a prova
	 * 
	 * @param provaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterarProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que cadastra um questão conforme prova selecionada
	 * 
	 * @param questaoNova
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravarQuestao(QuestaoDTO questaoNova)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que edita a questão selecionada
	 * 
	 * @param questaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterarQuestao(QuestaoDTO questaoSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que cadastra uma opção a questão selecionada
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravarOpcao(OpcaoDTO opcaoNova) throws ClassNotFoundException,
			SQLException;

	/**
	 * Método que consulta a lista de opções (respostas) por questaoId
	 * 
	 * @param questaoId
	 * @return List<OpcaoDAO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<OpcaoDTO> listarOpcoesPorQuestaoId(Integer questaoId)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que define a opção correta (qual é a resposta correta)
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void definirOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que excluir uma opção (resposta)
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void excluirOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que exclui uma questão, essa questão é excluida juntamento com as
	 * opções
	 * 
	 * @param questaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void excluirQuestao(QuestaoDTO questaoSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que consulta a lista de apostilas anexadas por provaId
	 * 
	 * @param provaId
	 * @return List<ApostilaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ApostilaDTO> listarApostilasPorProvaId(Integer provaId)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que exclui a prova, essa exclusão é em cascata. Remove a prova, as
	 * apostilas associadas, as questões e as opções
	 * 
	 * @param provaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void excluirProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException;
}
