package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * @author Roseli
 * 
 */
public class FuncionarioModelImpl implements FuncionarioModel {

	private static final Logger LOG = Logger
			.getLogger(FuncionarioModelImpl.class);

	private static final String SELECT_TODOS_FUNCIONARIO = "SELECT * FROM funcionario f, usuario u, perfil p "
			+ "WHERE f.usuario = u.usuario AND u.perfilId = p.id ORDER BY nome LIMIT ? OFFSET ?";

	private static final String SELECT_TODOS_PERFIS = "SELECT * FROM perfil";

	private static final String INSERT_USUARIO_FUNCIONARIO = "INSERT INTO usuario (usuario, senha, perfilId) "
			+ "VALUES (?, MD5(?), ?)";

	private static final String INSERT_FUNCIONARIO = "INSERT INTO funcionario (matricula, nome, funcao, email, usuario) "
			+ "VALUES (?, ?, ?, ?, ?)";

	private static final String SELECT_TOTAL_REGISTROS = "SELECT COUNT(matricula) AS total FROM funcionario";

	private static final String SELECT_FUNCIONARIO_POR_MATRICULA = "SELECT * FROM funcionario f, usuario u, perfil p "
			+ "WHERE f.usuario = u.usuario AND u.perfilId = p.id AND f.matricula = ?";

	private static final String INATIVAR_FUNCIONARIO_POR_MATRICULA = "UPDATE funcionario SET status = 'Inativo' WHERE matricula = ?";

	@Override
	public List<FuncionarioDTO> listarFuncionarios(Integer offSet,
			Integer recordPerPage) throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método listarFuncionarios");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<FuncionarioDTO> listaFuncionarios = new ArrayList<FuncionarioDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TODOS_FUNCIONARIO);
		pstmt.setInt(1, recordPerPage);
		pstmt.setInt(2, offSet);
		rs = pstmt.executeQuery();
		FuncionarioDTO func = null;

		while (rs.next()) {
			func = new FuncionarioDTO();
			func.setMatricula(rs.getInt("matricula"));
			func.setNome(rs.getString("nome"));
			func.setEmail(rs.getString("email"));
			func.setFuncao(rs.getString("funcao"));

			func.setUsuario(rs.getString("usuario"));

			func.setPerfilId(rs.getInt("id"));
			func.setDescricao(rs.getString("descricao"));
			func.setRole(rs.getString("rolename"));
			listaFuncionarios.add(func);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaFuncionarios;
	}

	@Override
	public List<PerfilDTO> listarPerfis() throws SQLException,
			ClassNotFoundException {

		LOG.info("Chamando método listarPerfis");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PerfilDTO> listaPerfis = new ArrayList<PerfilDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TODOS_PERFIS);
		rs = pstmt.executeQuery();
		PerfilDTO perfil = null;

		while (rs.next()) {
			perfil = new PerfilDTO();
			perfil.setPerfilId(rs.getInt("id"));
			perfil.setDescricao(rs.getString("descricao"));
			perfil.setRole(rs.getString("rolename"));
			listaPerfis.add(perfil);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaPerfis;
	}

	@Override
	public void gravar(FuncionarioDTO funcionario) throws SQLException,
			ClassNotFoundException {

		LOG.info("Chamando método gravar Funcionario");
		Connection conn = null;
		PreparedStatement pstmt = null;

		// TODO Há um bug nessa lógica
		/*
		 * Pode acontecer do sistema gravar o usuario e ter problemas em gravar
		 * o funcionario, então os dados não terão mais integridade. Seria
		 * interessante utilizar o conceito de TRANSACTION COMMIT/ROLLBACK
		 */

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_USUARIO_FUNCIONARIO);
		pstmt.setString(1, funcionario.getUsuario());
		pstmt.setString(2, funcionario.getSenha());
		pstmt.setInt(3, funcionario.getPerfilId());
		pstmt.execute();

		pstmt = conn.prepareStatement(INSERT_FUNCIONARIO);
		pstmt.setInt(1, funcionario.getMatricula());
		pstmt.setString(2, funcionario.getNome());
		pstmt.setString(3, funcionario.getFuncao());
		pstmt.setString(4, funcionario.getEmail());
		pstmt.setString(5, funcionario.getUsuario());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public Integer getTotalRegistros() throws SQLException,
			ClassNotFoundException {

		LOG.info("Chamando método gravar Funcionario");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRegistros = 0;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TOTAL_REGISTROS);
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
	public FuncionarioDTO buscarFuncionarioPorMatricula(Integer matricula)
			throws SQLException, ClassNotFoundException {
		LOG.info("Chamando método buscar Funcionario por matricula");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FuncionarioDTO func = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_FUNCIONARIO_POR_MATRICULA);
		pstmt.setInt(1, matricula);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			func = new FuncionarioDTO();
			func.setMatricula(rs.getInt("matricula"));
			func.setNome(rs.getString("nome"));
			func.setEmail(rs.getString("email"));
			func.setFuncao(rs.getString("funcao"));

			func.setUsuario(rs.getString("usuario"));

			func.setPerfilId(rs.getInt("id"));
			func.setDescricao(rs.getString("descricao"));
			func.setRole(rs.getString("rolename"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return func;
	}

	@Override
	public void inativarFuncionario(Integer matricula) throws SQLException,
			ClassNotFoundException {

		LOG.info("Chamando método inativar Funcionario por matricula");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INATIVAR_FUNCIONARIO_POR_MATRICULA);
		pstmt.setInt(1, matricula);
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}
}
