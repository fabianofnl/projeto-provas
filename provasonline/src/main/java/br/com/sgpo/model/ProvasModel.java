package br.com.sgpo.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;

/**
 * Interface que possui os m�todos de gerenciamento das provas
 * 
 * @author Roseli
 * 
 */
public interface ProvasModel {

	/**
	 * M�todo que cadastra a prova no sistema
	 * 
	 * @param provaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravar(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException;

	/**
	 * M�todo que consulta dados da prova por provaId
	 * 
	 * @param provaId
	 * @return ProvaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ProvaDTO buscarProvaPorId(Integer provaId)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo respons�vel pelo upload dos arquivos (apostilas)
	 * 
	 * @param apostilaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravaApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que remove a apostila
	 * 
	 * @param apostilaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void removerApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que consulta a lista de provas criadas no sistema
	 * 
	 * @return List<ProvaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ProvaDTO> listarProvas() throws ClassNotFoundException,
			SQLException;

	/**
	 * M�todo que altera a prova
	 * 
	 * @param provaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterarProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que cadastra um quest�o conforme prova selecionada
	 * 
	 * @param questaoNova
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravarQuestao(QuestaoDTO questaoNova)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que edita a quest�o selecionada
	 * 
	 * @param questaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterarQuestao(QuestaoDTO questaoSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que cadastra uma op��o a quest�o selecionada
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravarOpcao(OpcaoDTO opcaoNova) throws ClassNotFoundException,
			SQLException;

	/**
	 * M�todo que consulta a lista de op��es (respostas) por questaoId
	 * 
	 * @param questaoId
	 * @return List<OpcaoDAO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<OpcaoDTO> listarOpcoesPorQuestaoId(Integer questaoId)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que define a op��o correta (qual � a resposta correta)
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void definirOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que excluir uma op��o (resposta)
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void excluirOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que exclui uma quest�o, essa quest�o � excluida juntamento com as
	 * op��es
	 * 
	 * @param questaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void excluirQuestao(QuestaoDTO questaoSelecionada)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que consulta a lista de apostilas anexadas por provaId
	 * 
	 * @param provaId
	 * @return List<ApostilaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ApostilaDTO> listarApostilasPorProvaId(Integer provaId)
			throws ClassNotFoundException, SQLException;

	/**
	 * M�todo que exclui a prova, essa exclus�o � em cascata. Remove a prova, as
	 * apostilas associadas, as quest�es e as op��es
	 * 
	 * @param provaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public void excluirProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException;
}
