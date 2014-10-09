package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.TemasDTO;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * @author Roseli
 * 
 */
public class TemasModelImpl implements TemasModel {

	private static final Logger LOG = Logger.getLogger(TemasModelImpl.class);

	private static final String SELECT_TEMAS_PAGINADO = "SELECT *, (SELECT COUNT(q.questaoId) FROM questoes q "
			+ "WHERE t.temaId = q.questaoId) as quantidade FROM temas t ORDER BY t.titulo LIMIT ? OFFSET ?";

	private static final String SELECT_TOTAL_REGISTROS_TEMAS = "SELECT COUNT(titulo) AS total FROM temas";

	private static final String INSERT_TEMA = "INSERT INTO temas (titulo, descricao) VALUES (?, ?)";

	private static final String SELECT_TEMA_POR_ID = "SELECT * FROM temas WHERE temaId = ?";

	private static final String UPDATE_TEMA = "UPDATE temas SET titulo = ?, descricao = ? WHERE temaId = ?";

	private static final String DELETE_TEMA = "DELETE FROM temas WHERE temaId = ?";

	private static final String SELECT_TODOS_TEMAS = "SELECT * FROM temas";

	@Override
	public List<TemasDTO> listarTemas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarTemas paginados");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<TemasDTO> listaTemas = new ArrayList<TemasDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TEMAS_PAGINADO);
		pstmt.setInt(1, recordPerPage);
		pstmt.setInt(2, offSet);
		rs = pstmt.executeQuery();
		TemasDTO temas = null;

		while (rs.next()) {
			temas = new TemasDTO();
			temas.setTemaId(rs.getInt("temaId"));
			temas.setTitulo(rs.getString("titulo"));
			temas.setDescricao(rs.getString("descricao"));
			temas.setQuantidadeQuestoes(rs.getInt("quantidade"));
			listaTemas.add(temas);
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
	public void gravar(TemasDTO temasDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método gravar Tema");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_TEMA);
		pstmt.setString(1, temasDTO.getTitulo());
		pstmt.setString(2, temasDTO.getDescricao());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public Integer getTotalRegistrosTemas() throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método get Total Temas");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRegistros = 0;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TOTAL_REGISTROS_TEMAS);
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
	public TemasDTO buscarTemaporId(Integer temaId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método buscar Tema por id");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TemasDTO tema = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TEMA_POR_ID);
		pstmt.setInt(1, temaId);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			tema = new TemasDTO();
			tema.setTemaId(rs.getInt("temaId"));
			tema.setTitulo(rs.getString("titulo"));
			tema.setDescricao(rs.getString("descricao"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return tema;
	}

	@Override
	public void alterar(TemasDTO temasDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método alterar");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_TEMA);
		pstmt.setString(1, temasDTO.getTitulo());
		pstmt.setString(2, temasDTO.getDescricao());
		pstmt.setInt(3, temasDTO.getTemaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void remover(TemasDTO temasDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando método remover");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(DELETE_TEMA);
		pstmt.setInt(1, temasDTO.getTemaId());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	@Override
	public List<TemasDTO> listarTemas() throws ClassNotFoundException,
			SQLException {
		LOG.info("Chamando método listarTemas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<TemasDTO> listaTemas = new ArrayList<TemasDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TODOS_TEMAS);
		rs = pstmt.executeQuery();
		TemasDTO temas = null;

		while (rs.next()) {
			temas = new TemasDTO();
			temas.setTemaId(rs.getInt("temaId"));
			temas.setTitulo(rs.getString("titulo"));
			temas.setDescricao(rs.getString("descricao"));
			listaTemas.add(temas);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaTemas;
	}
}
