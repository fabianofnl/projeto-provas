package br.com.sgpo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sgpo.model.Funcionario;
import br.com.sgpo.model.Perfil;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * @author Roseli
 * 
 */
public class FuncionarioDaoImpl implements FuncionarioDao {

	private static final Logger LOG = Logger.getLogger(FuncionarioDaoImpl.class);
	
	private static final String SELECT_TODOS_FUNCIONARIO = "SELECT * FROM funcionario f, usuario u, perfil p "
			+ "WHERE f.usuario = u.usuario AND u.perfilId = p.id";

	private static final String SELECT_TODOS_PERFIS = "SELECT * FROM perfil";

	@Override
	public List<Funcionario> listarFuncionarios() throws ClassNotFoundException,
			SQLException {
		
		LOG.info("Chamando método listarFuncionarios");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TODOS_FUNCIONARIO);
		rs = pstmt.executeQuery();
		Funcionario func = null;

		while (rs.next()) {
			func = new Funcionario();
			func.setMatricula(rs.getInt("matricula"));
			func.setNome(rs.getString("nome"));
			func.setEmail(rs.getString("email"));

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
	public List<Perfil> listarPerfis() throws SQLException,
			ClassNotFoundException {
		
		LOG.info("Chamando método listarPerfis");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Perfil> listaPerfis = new ArrayList<Perfil>();
		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_TODOS_PERFIS);
		rs = pstmt.executeQuery();
		Perfil perfil = null;

		while (rs.next()) {
			perfil = new Perfil();
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
}
