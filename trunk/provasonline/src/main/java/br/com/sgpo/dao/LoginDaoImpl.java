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
 * Classe de implementa��o de m�todos de conex�o com a base de dados para
 * autentica��o de usu�rio do sistema.
 * 
 * @author Roseli
 * 
 */
public class LoginDaoImpl implements LoginDao {

	private static final Logger LOG = Logger.getLogger(LoginDaoImpl.class);

	public LoginDaoImpl() {
	}

	/**
	 * M�todo realiza conex�o com a base de dados e retorna objeto
	 * <b>usuario</b> para realizar a autentica��o.
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
					.prepareStatement("SELECT * FROM funcionario f, usuario u, perfil p WHERE "
							+ "p.perfilId = u.perfilId and u.matricula = f.matricula and "
							+ "u.nomeusuario = ?, u.senha = MD5(?)");
			pstmt.setString(1, nomeUsuario);
			pstmt.setString(2, senha);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				perfil = new Perfil();

				perfil.setPerfilId(rs.getInt("p.perfilId"));
				perfil.setDescricao(rs.getString("p.descricao"));
				perfil.setRole(rs.getString("p.roleName"));

				usuario.setMatricula(rs.getInt("u.matricula"));
				usuario.setNome(rs.getString("u.nome"));
				usuario.setNomeUsuario(rs.getString("u.usuarioNome"));
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
			LOG.error("Erro no processo de comunica��o com a base de dados.", e);
		}
		return usuario;
	}
}
