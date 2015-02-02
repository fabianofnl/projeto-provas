package br.com.sgpo.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.ApostilaDTO;
import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * Classe que implementa os métodos de gerenciamento das provas
 * 
 * @author Roseli
 * 
 */
public class ProvasModelImpl implements ProvasModel {

	private static final Logger LOG = Logger.getLogger(ProvasModelImpl.class);

	// TODO utilizado
	private static final String INSERT_PROVA = "INSERT INTO provas (titulo) VALUES (?)";

	// TODO utilizado
	private static final String SELECT_PROVA_POR_ID = "SELECT * FROM provas WHERE provaId = ?";

	// TODO utilizado
	private static final String INSERT_APOSTILA = "INSERT INTO apostilas (nome, provaId, extensao, hashName, serverPath) "
			+ "VALUES (?, ?, ?, ?, ?)";

	// TODO utilizado
	private static final String DELETE_APOSTILA = "DELETE FROM apostilas WHERE apostilaId = ?";

	// TODO utilizado
	private static final String SELECT_PROVAS = "SELECT * FROM provas ORDER BY titulo";

	// TODO utilizado
	private static final String SELECT_QUESTOES_POR_PROVA = "SELECT * FROM questoes "
			+ "WHERE provaId = ? ORDER BY titulo";

	// TODO utilizado
	private static final String SELECT_APOSTILA_POR_PROVA = "SELECT * FROM apostilas "
			+ "WHERE provaId = ? ORDER BY nome";

	// TODO utilizado
	private static final String PROVA_ALTERAR = "UPDATE provas SET titulo = ? WHERE provaId = ?";

	// TODO utilizado
	private static final String INSERT_QUESTAO = "INSERT INTO questoes (titulo, provaId, descricao, temaId) "
			+ "VALUES (?, ?, ?, ?)";

	// TODO utilizado
	private static final String UPDATE_QUESTAO = "UPDATE questoes SET titulo = ?, descricao = ?, temaId = ? "
			+ "WHERE questaoId = ?";

	// TODO utilizado
	private static final String INSERT_OPCAO = "INSERT INTO opcoes (titulo, questaoId) VALUES (?, ?)";

	// TODO utilizado
	private static final String SELECT_OPCOES_POR_QUESTAO = "SELECT * FROM opcoes WHERE questaoId = ? ORDER BY titulo";

	// TODO utilizado
	private static final String DEFINIR_OPCAO = "UPDATE opcoes SET flag = ? WHERE opcaoId = ?";

	// TODO utilizado
	private static final String DELETE_OPCAO = "DELETE FROM opcoes WHERE opcaoId = ?";

	// TODO utilizado
	private static final String DELETE_QUESTAO_COM_OPCOES = "BEGIN; DELETE FROM opcoes WHERE questaoId = ?; "
			+ "DELETE FROM questoes WHERE questaoId = ?; COMMIT;";

	// TODO utilizado
	private static final String SELECT_APOSTILA_POR_PROVA_ID = "SELECT * FROM apostilas WHERE provaId = ?";

	// TODO utilizado
	private static final String DELETE_PROVA_COM_QUESTOES_E_APOSTILAS = "BEGIN; DELETE FROM apostilas WHERE provaId = ?; "
			+ "DELETE FROM opcoes o WHERE o.questaoId IN (SELECT q.questaoId FROM questoes q WHERE q.provaId = ?); "
			+ "DELETE FROM questoes WHERE provaId = ?; "
			+ "DELETE FROM provas WHERE provaId = ?; COMMIT;";

	// TODO utilizado
	private static final String SELECT_QUANTIDADE_AGENDA_POR_PROVA = "SELECT COUNT(provaId) as quantidade "
			+ "FROM agenda WHERE provaId = ? AND flag = false AND dataprova >= CURRENT_DATE";

	/**
	 * Método que cadastra a prova no sistema
	 * 
	 * @param provaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravar(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método gravar Prova");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_PROVA);
		pstmt.setString(1, provaDTO.getTitulo());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	/**
	 * Método que consulta dados da prova por provaId
	 * 
	 * @param provaId
	 * @return ProvaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public ProvaDTO buscarProvaPorId(Integer provaId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método buscar Prova por id");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProvaDTO provaDTO = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_PROVA_POR_ID);
		pstmt.setInt(1, provaId);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			provaDTO = new ProvaDTO();
			provaDTO.setProvaId(rs.getInt("provaId"));
			provaDTO.setTitulo(rs.getString("titulo"));
		}

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		// TODO verificar o retorno no controller, pode acontecer NULLPOINTER

		return provaDTO;
	}

	/**
	 * Método responsável pelo upload dos arquivos (apostilas)
	 * 
	 * @param apostilaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravaApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método gravar Apostila");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_APOSTILA);
		pstmt.setString(1, apostilaDTO.getNome());
		pstmt.setInt(2, apostilaDTO.getProvaId());
		pstmt.setString(3, apostilaDTO.getExtensao());
		pstmt.setString(4, apostilaDTO.getHashName());
		pstmt.setString(5, apostilaDTO.getServerPath());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	/**
	 * Método que remove a apostila
	 * 
	 * @param apostilaDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	// TODO utilizado
	public void removerApostila(ApostilaDTO apostilaDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método remover apostila");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_APOSTILA);
		pstmt.setInt(1, apostilaDTO.getApostilaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	/**
	 * Método que consulta a lista de provas criadas no sistema
	 * 
	 * @return List<ProvaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<ProvaDTO> listarProvas() throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método listarProvas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ProvaDTO> listaProvas = new ArrayList<ProvaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_PROVAS);
		rs = pstmt.executeQuery();
		ProvaDTO prova = null;

		while (rs.next()) {
			prova = new ProvaDTO();
			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));
			listaProvas.add(prova);
		}

		for (ProvaDTO provaDTO : listaProvas) {
			provaDTO.setListaQuestoes(listarQuestoesPorProva(rs, pstmt, conn,
					provaDTO));
			provaDTO.setListaApostilas(listarApostilasPorProva(rs, pstmt, conn,
					provaDTO));
			provaDTO.setQuantidadeAgendada(quantidadeAgendadaPorProva(rs,
					pstmt, conn, provaDTO.getProvaId()));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaProvas;
	}

	/**
	 * Método que consulta a lista de questões por prova (re-aproveitamento)
	 * 
	 * @param rs
	 * @param pstmt
	 * @param conn
	 * @param provaDTO
	 * @return List<QuestaoDTO>
	 * @throws SQLException
	 */
	// TODO utilizado
	private List<QuestaoDTO> listarQuestoesPorProva(ResultSet rs,
			PreparedStatement pstmt, Connection conn, ProvaDTO provaDTO)
			throws SQLException {

		List<QuestaoDTO> listaQuestoes = new ArrayList<QuestaoDTO>();
		QuestaoDTO questao = null;
		pstmt = conn.prepareStatement(SELECT_QUESTOES_POR_PROVA);
		pstmt.setInt(1, provaDTO.getProvaId());
		rs = pstmt.executeQuery();

		while (rs.next()) {
			questao = new QuestaoDTO();
			questao.setQuestaoId(rs.getInt("questaoId"));
			questao.setProvaId(rs.getInt("provaId"));
			questao.setTituloQuestao(rs.getString("titulo"));
			questao.setDescricaoQuestao(rs.getString("descricao"));
			questao.setTemaId(rs.getInt("temaId"));
			listaQuestoes.add(questao);
		}

		return listaQuestoes;
	}

	/**
	 * Método que consulta a lista de apostilas por provas (re-aproveitamento)
	 * 
	 * @param rs
	 * @param pstmt
	 * @param conn
	 * @param provaDTO
	 * @return List<ApostilaDTO>
	 * @throws SQLException
	 */
	// TODO utilizado
	private List<ApostilaDTO> listarApostilasPorProva(ResultSet rs,
			PreparedStatement pstmt, Connection conn, ProvaDTO provaDTO)
			throws SQLException {

		List<ApostilaDTO> listaApostilas = new ArrayList<ApostilaDTO>();
		ApostilaDTO apostila = null;
		pstmt = conn.prepareStatement(SELECT_APOSTILA_POR_PROVA);
		pstmt.setInt(1, provaDTO.getProvaId());
		rs = pstmt.executeQuery();

		while (rs.next()) {
			apostila = new ApostilaDTO();
			apostila.setApostilaId(rs.getInt("apostilaId"));
			apostila.setProvaId(rs.getInt("provaId"));
			apostila.setNome(rs.getString("nome"));
			apostila.setHashName(rs.getString("hashname"));
			apostila.setExtensao(rs.getString("extensao"));
			apostila.setServerPath(rs.getString("serverpath"));
			listaApostilas.add(apostila);
		}

		return listaApostilas;
	}

	/**
	 * Método que altera a prova
	 * 
	 * @param provaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void alterarProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método alterar prova");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(PROVA_ALTERAR);
		pstmt.setString(1, provaSelecionada.getTitulo());
		pstmt.setInt(2, provaSelecionada.getProvaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	/**
	 * Método que cadastra um questão conforme prova selecionada
	 * 
	 * @param questaoNova
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravarQuestao(QuestaoDTO questaoNova)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método gravar Questao");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_QUESTAO);
		pstmt.setString(1, questaoNova.getTituloQuestao());
		pstmt.setInt(2, questaoNova.getProvaId());
		pstmt.setString(3, questaoNova.getDescricaoQuestao());
		pstmt.setInt(4, questaoNova.getTemaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	/**
	 * Método que edita a questão selecionada
	 * 
	 * @param questaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void alterarQuestao(QuestaoDTO questaoSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método alterar questão");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_QUESTAO);
		pstmt.setString(1, questaoSelecionada.getTituloQuestao());
		pstmt.setString(2, questaoSelecionada.getDescricaoQuestao());
		pstmt.setInt(3, questaoSelecionada.getTemaId());
		pstmt.setInt(4, questaoSelecionada.getQuestaoId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	/**
	 * Método que cadastra uma opção a questão selecionada
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravarOpcao(OpcaoDTO opcaoNova) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método gravar Opção");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_OPCAO);
		pstmt.setString(1, opcaoNova.getTituloOpcao());
		pstmt.setInt(2, opcaoNova.getQuestaoId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	/**
	 * Método que consulta a lista de opções (respostas) por questaoId
	 * 
	 * @param questaoId
	 * @return List<OpcaoDAO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<OpcaoDTO> listarOpcoesPorQuestaoId(Integer questaoId)
			throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarOpções");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<OpcaoDTO> listaOpcoes = new ArrayList<OpcaoDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_OPCOES_POR_QUESTAO);
		pstmt.setInt(1, questaoId);
		rs = pstmt.executeQuery();
		OpcaoDTO opcao = null;

		while (rs.next()) {
			opcao = new OpcaoDTO();
			opcao.setOpcaoId(rs.getInt("opcaoId"));
			opcao.setTituloOpcao(rs.getString("titulo"));
			opcao.setQuestaoId(rs.getInt("questaoId"));
			opcao.setFlag(rs.getBoolean("flag"));
			listaOpcoes.add(opcao);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaOpcoes;
	}

	/**
	 * Método que define a opção correta (qual é a resposta correta)
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void definirOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método definir Opção");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();

		List<OpcaoDTO> listaOpcoes = listarOpcoesPorQuestaoId(opcaoSelecionada
				.getQuestaoId());

		pstmt = conn.prepareStatement(DEFINIR_OPCAO);

		for (OpcaoDTO opcaoDTO : listaOpcoes) {
			if (opcaoDTO.getOpcaoId().equals(opcaoSelecionada.getOpcaoId())) {
				opcaoDTO.setFlag(true);
			} else {
				opcaoDTO.setFlag(false);
			}
			pstmt.setBoolean(1, opcaoDTO.getFlag());
			pstmt.setInt(2, opcaoDTO.getOpcaoId());
			pstmt.addBatch();
		}
		pstmt.executeBatch();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	/**
	 * Método que excluir uma opção (resposta)
	 * 
	 * @param opcaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void excluirOpcao(OpcaoDTO opcaoSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método remover Opção");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_OPCAO);
		pstmt.setInt(1, opcaoSelecionada.getOpcaoId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	/**
	 * Método que exclui uma questão, essa questão é excluida juntamento com as
	 * opções
	 * 
	 * @param questaoSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void excluirQuestao(QuestaoDTO questaoSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método excluir questao");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_QUESTAO_COM_OPCOES);
		pstmt.setInt(1, questaoSelecionada.getQuestaoId());
		pstmt.setInt(2, questaoSelecionada.getQuestaoId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	/**
	 * Método que consulta a lista de apostilas anexadas por provaId
	 * 
	 * @param provaId
	 * @return List<ApostilaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<ApostilaDTO> listarApostilasPorProvaId(Integer provaId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listar apostilas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ApostilaDTO> listaApostilas = new ArrayList<ApostilaDTO>();
		ApostilaDTO apostila = null;
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_APOSTILA_POR_PROVA_ID);
		pstmt.setInt(1, provaId);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			apostila = new ApostilaDTO();
			apostila.setApostilaId(rs.getInt("apostilaId"));
			apostila.setProvaId(rs.getInt("provaId"));
			apostila.setNome(rs.getString("nome"));
			apostila.setHashName(rs.getString("hashname"));
			apostila.setExtensao(rs.getString("extensao"));
			apostila.setServerPath(rs.getString("serverpath"));
			listaApostilas.add(apostila);
		}

		return listaApostilas;
	}

	/**
	 * Método que exclui a prova, essa exclusão é em cascata. Remove a prova, as
	 * apostilas associadas, as questões e as opções
	 * 
	 * @param provaSelecionada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	// TODO utilizado
	public void excluirProva(ProvaDTO provaSelecionada)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método excluir prova");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_PROVA_COM_QUESTOES_E_APOSTILAS);
		pstmt.setInt(1, provaSelecionada.getProvaId());
		pstmt.setInt(2, provaSelecionada.getProvaId());
		pstmt.setInt(3, provaSelecionada.getProvaId());
		pstmt.setInt(4, provaSelecionada.getProvaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	/**
	 * Método que consulta a quantidade de agendamento criados por prova
	 * (controle para evitar a exclusão da prova no mesmo dia da realização da
	 * mesma)
	 * 
	 * @param rs
	 * @param pstmt
	 * @param conn
	 * @param provaId
	 * @return Integer
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public Integer quantidadeAgendadaPorProva(ResultSet rs,
			PreparedStatement pstmt, Connection conn, Integer provaId)
			throws ClassNotFoundException, SQLException {

		Integer quantidade = 0;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_QUANTIDADE_AGENDA_POR_PROVA);
		pstmt.setInt(1, provaId);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			quantidade = rs.getInt("quantidade");
		}

		return quantidade;
	}
}
