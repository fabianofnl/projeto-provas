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

	public LoginModelImpl() {
	}

	/**
	 * Método realiza conexão com a base de dados e retorna objeto
	 * <b>usuario</b> para realizar a autenticação.
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
			LOG.error("Erro na execução da query.", e);
		} catch (ClassNotFoundException e) {
			LOG.error("Driver JDBC não encontrado.", e);
		}
		return funcionario;
	}
}
