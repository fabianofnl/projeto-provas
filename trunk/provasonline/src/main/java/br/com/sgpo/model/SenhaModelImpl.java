package br.com.sgpo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.util.ConexaoBaseDados;

/**
 * @author Roseli
 * 
 */
public class SenhaModelImpl implements SenhaModel {

	private static final Logger LOG = Logger.getLogger(SenhaModelImpl.class);

	private static final String SELECT_FUNCIONARIO_POR_USER_E_EMAIL = "SELECT * FROM usuario u, funcionario f "
			+ "WHERE u.usuario = f.usuario AND u.usuario = ? AND f.email = ?";

	private static final String UPDATE_SENHA = "UPDATE usuario SET senha = MD5(?) WHERE usuario = ?";

	@Override
	public FuncionarioDTO buscarUsuario(String nomeUsuario, String email)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método buscarUsuario");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(SELECT_FUNCIONARIO_POR_USER_E_EMAIL);
		pstmt.setString(1, nomeUsuario);
		pstmt.setString(2, email);
		rs = pstmt.executeQuery();
		FuncionarioDTO funcionario = null;

		if (rs.next()) {
			funcionario = new FuncionarioDTO();
			funcionario.setNome(rs.getString("nome"));
			funcionario.setUsuario(rs.getString("usuario"));
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
	public void alterarSenha(FuncionarioDTO funcionario)
			throws ClassNotFoundException, SQLException {

		LOG.info("Chamando método alterarSenha");
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = ConexaoBaseDados.getConexaoInstance();
		pstmt = conn.prepareStatement(UPDATE_SENHA);
		pstmt.setString(1, funcionario.getSenha());
		pstmt.setString(2, funcionario.getUsuario());
		pstmt.execute();

		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}
}
