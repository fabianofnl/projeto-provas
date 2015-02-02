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
	 * Método retorna objeto <b>funcionario</b> para realizar a autenticação.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return FuncionarioDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FuncionarioDTO logar(String nomeUsuario, String senha)
			throws ClassNotFoundException, SQLException;

	/**
	 * Método que altera a senha do usuário do sistema
	 * 
	 * @param funcionario
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String alterarSenha(FuncionarioDTO funcionario)
			throws ClassNotFoundException, SQLException;

}
