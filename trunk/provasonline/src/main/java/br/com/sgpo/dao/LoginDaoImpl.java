package br.com.sgpo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.sgpo.model.Usuario;
import br.com.sgpo.model.Perfil;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * Classe de implementação de métodos de conexão com a base de dados para
 * autenticação de usuário do sistema.
 * 
 * @author Roseli
 * 
 */
public class LoginDaoImpl implements LoginDao {

	private static final Logger LOG = Logger.getLogger(LoginDaoImpl.class);

	public LoginDaoImpl() {
	}

	/**
	 * Método realiza conexão com a base de dados e retorna objeto
	 * <b>usuario</b> para realizar a autenticação.
	 */
	@Override
	public Usuario logar(String nomeUsuario, String senha) {

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Usuario usuario = null;
		Perfil perfil = null;

		try {
			conn = ConexaoBaseDados.getConexaoInstance();
			pstmt = conn
					.prepareStatement("SELECT * FROM usuario u, perfil p WHERE "
							+ "p.perfilId = u.perfilId and u.nomeusuario = ? and u.senha = MD5(?)");
			pstmt.setString(1, nomeUsuario);
			pstmt.setString(2, senha);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				perfil = new Perfil();

				perfil.setPerfilId(rs.getInt("perfilid"));
				perfil.setDescricao(rs.getString("descricao"));
				perfil.setRole(rs.getString("rolename"));

				usuario.setMatricula(rs.getInt("matricula"));
				usuario.setNome(rs.getString("nome"));
				usuario.setNomeUsuario(rs.getString("nomeusuario"));
				usuario.setSenha(rs.getString("senha"));

				usuario.setPerfil(perfil);
			}

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			usuario = null;
			LOG.error("Erro na execução da query.", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Driver JDBC não encontrado.", e);
		}
		return usuario;
	}
}
