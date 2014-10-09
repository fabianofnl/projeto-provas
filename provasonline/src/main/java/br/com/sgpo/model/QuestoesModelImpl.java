package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.QuestaoDTO;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * @author Roseli
 * 
 */
public class QuestoesModelImpl implements QuestoesModel {

	private static final Logger LOG = Logger.getLogger(QuestoesModelImpl.class);

	private static final String SELECT_QUESTOES_PAGINADO = "SELECT *, (SELECT COUNT(o.opcaoId) FROM opcoes o "
			+ "WHERE q.questaoId = o.questaoId) as quantidade FROM questoes q ORDER BY q.titulo LIMIT ? OFFSET ?";

	private static final String SELECT_TOTAL_REGISTROS_QUESTOES = "SELECT COUNT(titulo) AS total FROM questoes";

	private static final String INSERT_QUESTAO = "INSERT INTO questoes (titulo, descricao, temaId) VALUES (?, ?, ?)";

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
}
