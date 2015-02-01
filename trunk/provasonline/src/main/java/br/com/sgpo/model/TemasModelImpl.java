package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.TemaDTO;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * Classe que implementa os m�todos de gerenciamento dos Temas
 * 
 * @author Roseli
 * 
 */
public class TemasModelImpl implements TemasModel {

	private static final Logger LOG = Logger.getLogger(TemasModelImpl.class);

	private static final String SELECT_TEMAS_PAGINADO = "SELECT *, (SELECT COUNT(q.questaoId) FROM questoes q "
			+ "WHERE t.temaId = q.temaId) as quantidade FROM temas t ORDER BY t.titulo LIMIT ? OFFSET ?";

	private static final String SELECT_TOTAL_REGISTROS_TEMAS = "SELECT COUNT(titulo) AS total FROM temas";

	// TODO utilizado
	private static final String INSERT_TEMA = "INSERT INTO temas (titulo, descricao) VALUES (?, ?)";

	private static final String SELECT_TEMA_POR_ID = "SELECT * FROM temas WHERE temaId = ?";

	// TODO utilizado
	private static final String UPDATE_TEMA = "UPDATE temas SET titulo = ?, descricao = ? WHERE temaId = ?";

	// TODO utilizado
	private static final String DELETE_TEMA = "DELETE FROM temas WHERE temaId = ?";

	// TODO utilizado
	private static final String SELECT_TODOS_TEMAS = "SELECT t.*, "
			+ "(SELECT COUNT(questaoId) FROM questoes q WHERE q.temaId = t.temaId) AS qtdQuestoes "
			+ "FROM temas t ORDER BY t.titulo";

	@Override
	public List<TemaDTO> listarTemas(Integer offSet, Integer recordPerPage)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando m�todo listarTemas paginados");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<TemaDTO> listaTemas = new ArrayList<TemaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TEMAS_PAGINADO);
		pstmt.setInt(1, recordPerPage);
		pstmt.setInt(2, offSet);
		rs = pstmt.executeQuery();
		TemaDTO temas = null;

		while (rs.next()) {
			temas = new TemaDTO();
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

	/**
	 * M�todo que cadastra os tema no sistema
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void gravar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando m�todo gravar Tema");
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

		LOG.info("Chamando m�todo get Total Temas");
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
	public TemaDTO buscarTemaporId(Integer temaId)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando m�todo buscar Tema por id");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TemaDTO tema = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TEMA_POR_ID);
		pstmt.setInt(1, temaId);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			tema = new TemaDTO();
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

	/**
	 * M�todo que altera um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void alterar(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando m�todo alterar");
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

	/**
	 * M�todo que exclui um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public void remover(TemaDTO temasDTO) throws ClassNotFoundException,
			SQLException {

		LOG.info("Chamando m�todo remover");
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

	/**
	 * M�todo que consulta a lista de temas cadastradas no sistema
	 * 
	 * @return List<TemaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public List<TemaDTO> listarTemas() throws ClassNotFoundException,
			SQLException {
		LOG.info("Chamando m�todo listarTemas");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<TemaDTO> listaTemas = new ArrayList<TemaDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TODOS_TEMAS);
		rs = pstmt.executeQuery();
		TemaDTO temas = null;

		while (rs.next()) {
			temas = new TemaDTO();
			temas.setTemaId(rs.getInt("temaId"));
			temas.setTitulo(rs.getString("titulo"));
			temas.setDescricao(rs.getString("descricao"));
			temas.setQuantidadeQuestoes(rs.getInt("qtdQuestoes"));
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
