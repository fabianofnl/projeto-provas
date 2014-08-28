package br.com.sgpo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.sgpo.model.Funcionario;
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
	public Funcionario logar(String nomeUsuario, String senha) {

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Funcionario funcionario = null;

		try {
			conn = ConexaoBaseDados.getConexaoInstance();
			pstmt = conn
					.prepareStatement("SELECT * FROM usuario u, perfil p, funcionario f WHERE "
							+ "p.perfilId = u.perfilId and u.nomeusuario = f.u.nomeusuario and "
							+ "u.nomeusuario = ? and u.senha = MD5(?)");
			pstmt.setString(1, nomeUsuario);
			pstmt.setString(2, senha);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				funcionario = new Funcionario();

				funcionario.setPerfilId(rs.getInt("usuarioid"));
				funcionario.setDescricao(rs.getString("descricao"));
				funcionario.setRole(rs.getString("rolename"));
				funcionario.setMatricula(rs.getInt("matricula"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setNomeUsuario(rs.getString("nomeusuario"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setEmail(rs.getString("email"));
			}

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			funcionario = null;
			LOG.error("Erro na execu��o da query.", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Driver JDBC n�o encontrado.", e);
		}
		return funcionario;
	}
}
