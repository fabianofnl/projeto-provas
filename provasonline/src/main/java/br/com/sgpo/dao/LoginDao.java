package br.com.sgpo.dao;

import br.com.sgpo.model.Funcionario;

/**
 * Interface de conexão com a base de dados para autenticação de usuários do
 * sistema.
 * 
 * @author Roseli
 * 
 */
public interface LoginDao {

	/**
	 * Método realiza conexão com a base de dados e retorna objeto
	 * <b>funcionario</b> para realizar a autenticação.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return funcionario
	 */
	public Funcionario logar(String nomeUsuario, String senha);

}
