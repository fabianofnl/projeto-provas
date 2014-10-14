package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.ProvaDTO;
import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.util.ConexaoBaseDados;

public class ProvasModelImpl implements ProvasModel {

	private static final Logger LOG = Logger.getLogger(ProvasModelImpl.class);

	private static final String SELECT_PROVAS_PAGINADO = "SELECT * FROM provas ORDER BY titulo LIMIT ? OFFSET ?";

	private static final String SELECT_TOTAL_REGISTROS_PROVAS = "SELECT COUNT(provaId) AS total FROM provas";

	private static final String SELECT_TEMAS_POR_PROVA = "SELECT DISTINCT ON(t.temaId) t.* "
			+ "FROM provas p, montarProvas mp, questoes q, temas t "
			+ "WHERE t.temaId = q.temaId AND q.questaoId = mp.questaoId AND mp.provaId = p.provaId AND p.provaId = ?";

	private static final String SELECT_QUESTOES_POR_TEMA = "SELECT q.* FROM temas t, questoes q, montarProvas mp "
			+ "WHERE t.temaId = q.temaId AND q.questaoId = mp.questaoId AND mp.provaId = ? AND t.temaId = ?";

	private static final String INSERT_PROVA = "INSERT INTO provas (titulo) VALUES (?)";

	private static final String SELECT_PROVA_POR_ID = "SELECT * FROM provas WHERE provaId = ?";

	private static final String DELETE_QUESTAO_PROVA = "DELETE FROM montarProvas WHERE questaoId = ? AND provaId = ?";

	private static final String INSERT_PROVAS_QUESTOES = "INSERT INTO montarProvas (provaId, questaoId) VALUES (?, ?)";

	private static final String DELETE_PROVA = "DELETE FROM provas WHERE provaId = ?";

	@Override
	public List<ProvaDTO> listarProvas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarProvas paginados");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ProvaDTO> listaProvas = new ArrayList<ProvaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_PROVAS_PAGINADO);
		pstmt.setInt(1, recordPerPage);
		pstmt.setInt(2, offSet);
		rs = pstmt.executeQuery();
		ProvaDTO prova = null;

		while (rs.next()) {
			prova = new ProvaDTO();
			prova.setProvaId(rs.getInt("provaId"));
			prova.setTitulo(rs.getString("titulo"));
			listaProvas.add(prova);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaProvas;
	}

	@Override
	public List<TemaDTO> listarTemasPorProva(Integer provaId)
			throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarTemas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<TemaDTO> listaTemas = new ArrayList<TemaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TEMAS_POR_PROVA);
		pstmt.setInt(1, provaId);
		rs = pstmt.executeQuery();
		TemaDTO tema = null;

		while (rs.next()) {
			tema = new TemaDTO();
			tema.setTemaId(rs.getInt("temaId"));
			tema.setTitulo(rs.getString("titulo"));
			listaTemas.add(tema);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaTemas;
	}

	@Override
	public List<QuestaoDTO> listarQuestoesPorTemas(Integer temaId,
			Integer provaId) throws ClassNotFoundException, SQLException {
		LOG.info("Chamando método listarQuestoes");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<QuestaoDTO> listaQuestoes = new ArrayList<QuestaoDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_QUESTOES_POR_TEMA);
		pstmt.setInt(1, provaId);
		pstmt.setInt(2, temaId);
		rs = pstmt.executeQuery();
		QuestaoDTO questao = null;

		while (rs.next()) {
			questao = new QuestaoDTO();
			questao.setQuestaoId(rs.getInt("questaoId"));
			questao.setTituloQuestao(rs.getString("titulo"));
			questao.setDescricaoQuestao(rs.getString("descricao"));
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

	@Override
	public Integer getTotalRegistrosProvas() throws ClassNotFoundException,
			SQLException {
		LOG.info("Chamando método get Total Provas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRegistros = 0;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TOTAL_REGISTROS_PROVAS);
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

	@Override
	public void removerQuestao(QuestaoDTO questaoDTO, ProvaDTO provaDTO)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método remover questão provas");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_QUESTAO_PROVA);
		pstmt.setInt(1, questaoDTO.getQuestaoId());
		pstmt.setInt(2, provaDTO.getProvaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void associaProvaQuestoes(Integer provaId, Integer[] questoesId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método provas questões");

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_PROVAS_QUESTOES);

		for (int i = 0; i < questoesId.length; i++) {
			pstmt.setInt(1, provaId);
			pstmt.setInt(2, questoesId[i]);
			pstmt.addBatch();
		}

		pstmt.executeBatch();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void remover(ProvaDTO provaDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método remover");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_PROVA);
		pstmt.setInt(1, provaDTO.getProvaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}
}
