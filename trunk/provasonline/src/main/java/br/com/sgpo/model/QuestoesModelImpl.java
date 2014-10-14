package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.OpcaoDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * @author Roseli
 * 
 */
public class QuestoesModelImpl implements QuestoesModel {

	private static final Logger LOG = Logger.getLogger(QuestoesModelImpl.class);

	private static final String SELECT_QUESTOES_PAGINADO = "SELECT q.*, "
			+ "t.temaId, t.titulo as tituloTema, t.descricao as descricaoTema, (SELECT COUNT(o.opcaoId) FROM opcoes o "
			+ "WHERE q.questaoId = o.questaoId) as quantidade FROM questoes q, temas t WHERE q.temaId = t.temaId "
			+ "ORDER BY t.titulo, q.titulo LIMIT ? OFFSET ?";

	private static final String SELECT_TOTAL_REGISTROS_QUESTOES = "SELECT COUNT(titulo) AS total FROM questoes";

	private static final String INSERT_QUESTAO = "INSERT INTO questoes (titulo, descricao, temaId) VALUES (?, ?, ?)";

	private static final String SELECT_OPCOES_POR_QUESTAO = "SELECT o.*, (SELECT COUNT(m.provaId) "
			+ "FROM montarProvas m, questoes q WHERE m.questaoId = q.questaoId AND q.questaoId = o.questaoId) AS quantidadeProvas "
			+ "FROM opcoes o WHERE o.questaoId = ?";

	private static final String SELECT_QUESTAO_POR_ID = "SELECT q.*, "
			+ "t.temaId, t.titulo as tituloTema, t.descricao as descricaoTema FROM questoes q, temas t "
			+ "WHERE q.temaId = t.temaId and q.questaoId = ?";

	private static final String DELETE_QUESTAO = "DELETE FROM questoes WHERE questaoId = ?";

	private static final String UPDATE_QUESTAO = "UPDATE questoes SET titulo = ?, descricao = ?, temaId = ? "
			+ "WHERE questaoId = ?";

	private static final String INSERT_OPCAO = "INSERT INTO opcoes (titulo, questaoId) VALUES (?, ?)";

	private static final String SELECT_OPCAO_POR_ID = "SELECT q.*, "
			+ "t.temaId, t.titulo as tituloTema, t.descricao as descricaoTema, o.opcaoId, o.titulo as tituloOpcao, "
			+ "o.flag FROM questoes q, temas t, opcoes o "
			+ "WHERE q.temaId = t.temaId and o.questaoId = q.questaoId and o.opcaoId = ?";

	private static final String DELETE_OPCAO = "DELETE FROM opcoes WHERE opcaoId = ?";

	private static final String DEFINIR_OPCAO = "UPDATE opcoes SET flag = ? WHERE opcaoId = ?";

	private static final String UPDATE_OPCAO = "UPDATE opcoes SET titulo = ? WHERE opcaoId = ?";

	@Override
	public List<QuestaoDTO> listarQuestoes(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarQuestoes paginados");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<QuestaoDTO> listaQuestoes = new ArrayList<QuestaoDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_QUESTOES_PAGINADO);
		pstmt.setInt(1, recordPerPage);
		pstmt.setInt(2, offSet);
		rs = pstmt.executeQuery();
		QuestaoDTO questao = null;

		while (rs.next()) {
			questao = new QuestaoDTO();
			questao.setQuestaoId(rs.getInt("questaoId"));
			questao.setTituloQuestao(rs.getString("titulo"));
			questao.setDescricaoQuestao(rs.getString("descricao"));
			questao.setQuantidadeOpcoes(rs.getInt("quantidade"));

			questao.setTemaId(rs.getInt("temaId"));
			questao.setTitulo(rs.getString("tituloTema"));
			questao.setDescricao(rs.getString("descricaoTema"));
			listaQuestoes.add(questao);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaQuestoes;
	}

	@Override
	public Integer getTotalRegistrosQuestoes() throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método get Total Questões");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRegistros = 0;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TOTAL_REGISTROS_QUESTOES);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			totalRegistros = rs.getInt("total");
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return totalRegistros;
	}

	@Override
	public void gravar(QuestaoDTO questaoDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método gravar Questao");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_QUESTAO);
		pstmt.setString(1, questaoDTO.getTituloQuestao());
		pstmt.setString(2, questaoDTO.getDescricaoQuestao());
		pstmt.setInt(3, questaoDTO.getTemaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
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
			opcao.setQuantidadeProvas(rs.getInt("quantidadeProvas"));
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

	@Override
	public QuestaoDTO buscarQuestaoPorId(Integer questaoId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método buscarQuestaoPorId");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_QUESTAO_POR_ID);
		pstmt.setInt(1, questaoId);
		rs = pstmt.executeQuery();
		QuestaoDTO questao = null;

		if (rs.next()) {
			questao = new QuestaoDTO();
			questao.setQuestaoId(rs.getInt("questaoId"));
			questao.setTituloQuestao(rs.getString("titulo"));
			questao.setDescricaoQuestao(rs.getString("descricao"));

			questao.setTemaId(rs.getInt("temaId"));
			questao.setTitulo(rs.getString("tituloTema"));
			questao.setDescricao(rs.getString("descricaoTema"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return questao;
	}

	@Override
	public void remover(QuestaoDTO questaoDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método remover");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_QUESTAO);
		pstmt.setInt(1, questaoDTO.getQuestaoId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void alterar(QuestaoDTO questaoDTO, Integer questaoIdAntiga)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método alterar");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_QUESTAO);
		pstmt.setString(1, questaoDTO.getTituloQuestao());
		pstmt.setString(2, questaoDTO.getDescricaoQuestao());
		pstmt.setInt(3, questaoDTO.getTemaId());
		pstmt.setInt(4, questaoIdAntiga);
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void gravarOpcao(OpcaoDTO opcao) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método gravar Opção");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_OPCAO);
		pstmt.setString(1, opcao.getTituloOpcao());
		pstmt.setInt(2, opcao.getQuestaoId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public OpcaoDTO buscarOpcaoPorId(Integer opcaoId)
			throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método buscarQuestaoPorId");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_OPCAO_POR_ID);
		pstmt.setInt(1, opcaoId);
		rs = pstmt.executeQuery();
		OpcaoDTO opcao = null;

		if (rs.next()) {
			opcao = new OpcaoDTO();
			opcao.setQuestaoId(rs.getInt("questaoId"));
			opcao.setTituloQuestao(rs.getString("titulo"));
			opcao.setDescricaoQuestao(rs.getString("descricao"));

			opcao.setTemaId(rs.getInt("temaId"));
			opcao.setTitulo(rs.getString("tituloTema"));
			opcao.setDescricao(rs.getString("descricaoTema"));

			opcao.setOpcaoId(rs.getInt("opcaoId"));
			opcao.setTituloOpcao(rs.getString("tituloOpcao"));
			opcao.setFlag(rs.getBoolean("flag"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return opcao;
	}

	@Override
	public void removerOpcao(OpcaoDTO opcaoDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método remover Opção");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_OPCAO);
		pstmt.setInt(1, opcaoDTO.getOpcaoId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void definirOpcao(List<OpcaoDTO> listaOpcoes)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método definir Opção");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DEFINIR_OPCAO);

		for (OpcaoDTO opcaoDTO : listaOpcoes) {
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

	@Override
	public void alterarOpcao(OpcaoDTO opcaoDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método alterar Opção");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_OPCAO);
		pstmt.setString(1, opcaoDTO.getTituloOpcao());
		pstmt.setInt(2, opcaoDTO.getOpcaoId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}
}
