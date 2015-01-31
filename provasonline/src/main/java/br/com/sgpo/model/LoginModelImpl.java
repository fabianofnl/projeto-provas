package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * Classe de implementação de métodos de conexão com a base de dados para
 * autenticação de usuário do sistema.
 * 
 * @author Roseli
 * 
 */
public class LoginModelImpl implements LoginModel {

	private static final Logger LOG = Logger.getLogger(LoginModelImpl.class);

	private static final String SELECT_FUNCIONARIO_LOGIN = "SELECT * FROM perfil p, usuario u, funcionario f "
			+ "WHERE f.status != 'Inativo' AND p.id = u.perfilId AND u.usuario = f.usuario AND "
			+ "u.usuario = ? AND u.senha = MD5(?)";

	private static final String UPDATE_SENHA_USUARIO = "UPDATE usuario SET senha = MD5(?) "
			+ "FROM funcionario "
			+ "WHERE usuario.usuario = ? AND funcionario.email = ? "
			+ "RETURNING usuario.usuario";

	public LoginModelImpl() {
	}

	/**
	 * Método realiza conexão com a base de dados e retorna objeto
	 * <b>usuario</b> para realizar a autenticação.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public FuncionarioDTO logar(String nomeUsuario, String senha)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando logar");

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		FuncionarioDTO funcionario = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_FUNCIONARIO_LOGIN);
		pstmt.setString(1, nomeUsuario);
		pstmt.setString(2, senha);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			funcionario = new FuncionarioDTO();

			funcionario.setPerfilId(rs.getInt("id"));
			funcionario.setDescricao(rs.getString("descricao"));
			funcionario.setRole(rs.getString("roleName"));
			funcionario.setMatricula(rs.getInt("matricula"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setUsuario(rs.getString("usuario"));
			funcionario.setSenha(rs.getString("senha"));
			funcionario.setEmail(rs.getString("email"));
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return funcionario;
	}

	@Override
	public String alterarSenha(FuncionarioDTO funcionario)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando alterar senha");

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String username = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_SENHA_USUARIO);
		pstmt.setString(1, funcionario.getSenha());
		pstmt.setString(2, funcionario.getUsuario());
		pstmt.setString(3, funcionario.getEmail());
		rs = pstmt.executeQuery();

		if (rs.next()) {
			username = rs.getString("usuario");
		}

		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();

		return username;
	}
}
