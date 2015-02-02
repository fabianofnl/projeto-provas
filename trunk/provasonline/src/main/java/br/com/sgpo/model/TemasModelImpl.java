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
 * Classe que implementa os métodos de gerenciamento dos Temas
 * 
 * @author Roseli
 * 
 */
public class TemasModelImpl implements TemasModel {

	private static final Logger LOG = Logger.getLogger(TemasModelImpl.class);

	private static final String INSERT_TEMA = "INSERT INTO temas (titulo, descricao) VALUES (?, ?)";

	private static final String UPDATE_TEMA = "UPDATE temas SET titulo = ?, descricao = ? WHERE temaId = ?";

	private static final String DELETE_TEMA = "DELETE FROM temas WHERE temaId = ?";

	private static final String SELECT_TODOS_TEMAS = "SELECT t.*, "
			+ "(SELECT COUNT(questaoId) FROM questoes q WHERE q.temaId = t.temaId) AS qtdQuestoes "
			+ "FROM temas t ORDER BY t.titulo";

	/**
	 * Método que cadastra os tema no sistema
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void gravar(TemaDTO temasDTO) throws ClassNotFoundException,
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

	/**
	 * Método que altera um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(TemaDTO temasDTO) throws ClassNotFoundException,
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

	/**
	 * Método que exclui um tema selecionado
	 * 
	 * @param temasDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void remover(TemaDTO temasDTO) throws ClassNotFoundException,
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

	/**
	 * Método que consulta a lista de temas cadastradas no sistema
	 * 
	 * @return List<TemaDTO>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TemaDTO> listarTemas() throws ClassNotFoundException,
			SQLException {
		LOG.info("Chamando método listarTemas");

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
