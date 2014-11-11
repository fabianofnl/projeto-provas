package br.com.sgpo.service;

import java.sql.SQLException;

import br.com.sgpo.dto.FuncionarioDTO;

/**
 * @author Roseli
 * 
 */
public interface SenhaService {

	public FuncionarioDTO buscarUsuario(String nomeUsuario, String email)
			throws ClassNotFoundException, SQLException;

	public void alterarSenha(FuncionarioDTO funcionario, String contextPath) throws ClassNotFoundException,
			SQLException;

}
