package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.EquipeDTO;
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

	private static final String SELECT_TOTAL_REGISTROS_FUNCIONARIOS = "SELECT COUNT(matricula) AS total FROM funcionario";

	private static final String SELECT_FUNCIONARIO_POR_MATRICULA = "SELECT * FROM funcionario f, usuario u, perfil p "
			+ "WHERE f.usuario = u.usuario AND u.perfilId = p.id AND f.matricula = ?";

	private static final String INATIVAR_FUNCIONARIO_POR_MATRICULA = "UPDATE funcionario SET status = 'Inativo' WHERE matricula = ?";

	private static final String UPDATE_USUARIO_FUNCIONARIO = "UPDATE usuario SET perfilid = ? WHERE usuario = ?";

	private static final String UPDATE_FUNCIONARIO = "UPDATE funcionario SET matricula = ?, nome = ?, funcao = ?, email = ?, "
			+ "status = ? WHERE matricula = ?";

	private static final String SELECT_GERENTES = "SELECT * FROM funcionario f, usuario u, perfil p "
			+ "WHERE f.usuario = u.usuario AND u.perfilId = p.id AND p.descricao ILIKE 'Gerente'";

	private static final String SELECT_COLABORADORES = "SELECT * FROM funcionario f, usuario u, perfil p "
			+ "WHERE f.usuario = u.usuario AND u.perfilId = p.id AND p.descricao ILIKE 'Colaborador' "
			+ "AND f.matricula NOT IN (SELECT matcolaborador FROM equipes)";

	private static final String INSERT_EQUIPES = "INSERT INTO equipes (matgerente, matcolaborador) VALUES (?, ?)";

	private static final String SELECT_TODAS_EQUIPES = "SELECT DISTINCT ON (f.nome) f.nome, * "
			+ "FROM funcionario f, usuario u, perfil p, equipes e "
			+ "WHERE f.usuario = u.usuario AND u.perfilID = p.id AND f.matricula = e.matgerente "
			+ "ORDER BY f.nome LIMIT ? OFFSET ?";

	private static final String SELECT_TOTAL_REGISTROS_EQUIPES = "SELECT COUNT(DISTINCT(matgerente)) AS total FROM equipes";

	private static final String SELECT_COLABORADORES_POR_GERENTE = "SELECT * FROM funcionario f, usuario u, perfil p, equipes e "
			+ "WHERE f.usuario = u.usuario AND u.perfilId = p.id AND f.matricula = e.matcolaborador AND e.matgerente = ?";

	private static final String REMOVER_COLABORADOR_POR_MATRICULA = "DELETE FROM equipes WHERE matcolaborador = ?";

	@Override
	public List<FuncionarioDTO> listarFuncionarios(Integer offSet,
			Integer recordPerPage) throws ClassNotFoundException, SQLException {

		LOG.info("Chamando m�todo listarFuncionarios");

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
			func.setStatus(rs.getString("status"));

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

		LOG.info("Chamando m�todo listarPerfis");
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

		LOG.info("Chamando m�todo gravar Funcionario");
		Connection conn = null;
		PreparedStatement pstmt = null;

		// TODO H� um bug nessa l�gica
		/*
		 * Pode acontecer do sistema gravar o usuario e ter problemas em gravar
		 * o funcionario, ent�o os dados n�o ter�o mais integridade. Seria
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
	public Integer getTotalRegistrosFuncionarios() throws SQLException,
			ClassNotFoundException {

		LOG.info("Chamando m�todo get Total Funcionarios");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRegistros = 0;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TOTAL_REGISTROS_FUNCIONARIOS);
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
		LOG.info("Chamando m�todo buscar Funcionario por matricula");
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
			func.setStatus(rs.getString("status"));

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
	public void inativar(Integer matricula) throws SQLException,
			ClassNotFoundException {

		LOG.info("Chamando m�todo inativar Funcionario por matricula");
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

	@Override
	public void alterar(FuncionarioDTO funcionario, Integer matriculaAntiga)
			throws SQLException, ClassNotFoundException {

		LOG.info("Chamando m�todo alterar Funcionario");
		Connection conn = null;
		PreparedStatement pstmt = null;

		// TODO H� um bug nessa l�gica
		/*
		 * Pode acontecer do sistema gravar o usuario e ter problemas em gravar
		 * o funcionario, ent�o os dados n�o ter�o mais integridade. Seria
		 * interessante utilizar o conceito de TRANSACTION COMMIT/ROLLBACK
		 */

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_USUARIO_FUNCIONARIO);
		pstmt.setInt(1, funcionario.getPerfilId());
		pstmt.setString(2, funcionario.getUsuario());
		pstmt.execute();

		pstmt = conn.prepareStatement(UPDATE_FUNCIONARIO);
		pstmt.setInt(1, funcionario.getMatricula());
		pstmt.setString(2, funcionario.getNome());
		pstmt.setString(3, funcionario.getFuncao());
		pstmt.setString(4, funcionario.getEmail());
		pstmt.setString(5, funcionario.getStatus());
		pstmt.setInt(6, matriculaAntiga);
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public List<FuncionarioDTO> listarGerentes() throws SQLException,
			ClassNotFoundException {
		LOG.info("Chamando m�todo listarGerentes");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<FuncionarioDTO> listaFuncionarios = new ArrayList<FuncionarioDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_GERENTES);
		rs = pstmt.executeQuery();
		FuncionarioDTO func = null;

		while (rs.next()) {
			func = new FuncionarioDTO();
			func.setMatricula(rs.getInt("matricula"));
			func.setNome(rs.getString("nome"));
			func.setEmail(rs.getString("email"));
			func.setFuncao(rs.getString("funcao"));
			func.setStatus(rs.getString("status"));

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
	public List<FuncionarioDTO> listarColaboradores() throws SQLException,
			ClassNotFoundException {
		LOG.info("Chamando m�todo listarColaboradores");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<FuncionarioDTO> listaFuncionarios = new ArrayList<FuncionarioDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_COLABORADORES);
		rs = pstmt.executeQuery();
		FuncionarioDTO func = null;

		while (rs.next()) {
			func = new FuncionarioDTO();
			func.setMatricula(rs.getInt("matricula"));
			func.setNome(rs.getString("nome"));
			func.setEmail(rs.getString("email"));
			func.setFuncao(rs.getString("funcao"));
			func.setStatus(rs.getString("status"));

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
	public void associarEquipes(Integer matGerente, Integer[] matColaborador)
			throws SQLException, ClassNotFoundException {

		LOG.info("Chamando m�todo associarEquipes");

		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(INSERT_EQUIPES);

		for (int i = 0; i < matColaborador.length; i++) {
			pstmt.setInt(1, matGerente);
			pstmt.setInt(2, matColaborador[i]);
			pstmt.addBatch();
		}

		pstmt.executeBatch();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

	}

	@Override
	public List<EquipeDTO> listarEquipes(Integer offSet, Integer recordPerPage)
			throws SQLException, ClassNotFoundException {

		LOG.info("Chamando m�todo listarEquipes");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<EquipeDTO> listaEquipes = new ArrayList<EquipeDTO>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TODAS_EQUIPES);
		pstmt.setInt(1, recordPerPage);
		pstmt.setInt(2, offSet);
		rs = pstmt.executeQuery();

		FuncionarioDTO func = null;
		EquipeDTO equipe = null;

		while (rs.next()) {
			func = new FuncionarioDTO();
			equipe = new EquipeDTO();
			func.setMatricula(rs.getInt("matricula"));
			func.setNome(rs.getString("nome"));
			func.setEmail(rs.getString("email"));
			func.setFuncao(rs.getString("funcao"));
			func.setStatus(rs.getString("status"));

			func.setUsuario(rs.getString("usuario"));

			func.setPerfilId(rs.getInt("id"));
			func.setDescricao(rs.getString("descricao"));
			func.setRole(rs.getString("rolename"));

			equipe.setGerente(func);
			listaEquipes.add(equipe);
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return listaEquipes;
	}

	@Override
	public Integer getTotalRegistrosEquipes() throws SQLException,
			ClassNotFoundException {

		LOG.info("Chamando m�todo get Total Equipes");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer totalRegistros = 0;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TOTAL_REGISTROS_EQUIPES);
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
	public List<FuncionarioDTO> listarColaboradorPorGerente(Integer matricula)
			throws SQLException, ClassNotFoundException {

		LOG.info("Chamando m�todo listar Colaboradores por Gerente");

		List<FuncionarioDTO> listaColaboradores = new ArrayList<FuncionarioDTO>();
		FuncionarioDTO func = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_COLABORADORES_POR_GERENTE);
		pstmt.setInt(1, matricula);
		rs = pstmt.executeQuery();

		while (rs.next()) {

			func = new FuncionarioDTO();
			func.setMatricula(rs.getInt("matricula"));
			func.setNome(rs.getString("nome"));
			func.setEmail(rs.getString("email"));
			func.setFuncao(rs.getString("funcao"));
			func.setStatus(rs.getString("status"));

			func.setUsuario(rs.getString("usuario"));

			func.setPerfilId(rs.getInt("id"));
			func.setDescricao(rs.getString("descricao"));
			func.setRole(rs.getString("rolename"));
			listaColaboradores.add(func);
		}

		return listaColaboradores;
	}

	@Override
	public void removerColaborador(Integer matricula) throws SQLException,
			ClassNotFoundException {

		LOG.info("Chamando m�todo remover Colaborador por matricula");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(REMOVER_COLABORADOR_POR_MATRICULA);
		pstmt.setInt(1, matricula);
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}
}