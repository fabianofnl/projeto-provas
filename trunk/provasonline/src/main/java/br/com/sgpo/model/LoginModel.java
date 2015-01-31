package br.com.sgpo.model;

import java.sql.SQLException;

import br.com.sgpo.dto.FuncionarioDTO;

/**
 * Interface de conexão com a base de dados para autenticação de usuários do
 * sistema.
 * 
 * @author Roseli
 * 
 */
public interface LoginModel {

	/**
	 * Método realiza conexão com a base de dados e retorna objeto
	 * <b>funcionario</b> para realizar a autenticação.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return funcionario
	 */
	public FuncionarioDTO logar(String nomeUsuario, String senha)
			throws ClassNotFoundException, SQLException;

	public String alterarSenha(FuncionarioDTO funcionario)
			throws ClassNotFoundException, SQLException;

}
