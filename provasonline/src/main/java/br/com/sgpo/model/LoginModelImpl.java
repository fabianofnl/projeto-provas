package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * Classe de implementa��o de m�todos de conex�o com a base de dados para
 * autentica��o de usu�rio do sistema.
 * 
 * @author Roseli
 * 
 */
public class LoginModelImpl implements LoginModel {

	private static final Logger LOG = Logger.getLogger(LoginModelImpl.class);

	public LoginModelImpl() {
	}

	/**
	 * M�todo realiza conex�o com a base de dados e retorna objeto
	 * <b>usuario</b> para realizar a autentica��o.
	 */
	@Override
	public FuncionarioDTO logar(String nomeUsuario, String senha) {

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		FuncionarioDTO funcionario = null;

		try {
			conn = ConexaoBaseDados.getConexaoInstance();
			pstmt = conn
					.prepareStatement("SELECT * FROM perfil p, usuario u, funcionario f "
							+ "WHERE p.id = u.perfilId AND u.usuario = f.usuario AND "
							+ "u.usuario = ? AND u.senha = MD5(?)");
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

		} catch (SQLException e) {
			funcionario = null;
			LOG.error("Erro na execu��o da query.", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Driver JDBC n�o encontrado.", e);
		}
		return funcionario;
	}
}
