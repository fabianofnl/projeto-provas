package br.com.sgpo.model;

import java.sql.SQLException;

import br.com.sgpo.dto.FuncionarioDTO;

/**
 * @author Roseli
 * 
 */
public interface SenhaModel {

	public FuncionarioDTO buscarUsuario(String nomeUsuario, String email)
			throws ClassNotFoundException, SQLException;

	public void alterarSenha(FuncionarioDTO funcionario) throws ClassNotFoundException,
			SQLException;

}
