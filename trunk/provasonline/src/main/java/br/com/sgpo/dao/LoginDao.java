package br.com.sgpo.dao;

import br.com.sgpo.model.Usuario;

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
	 * <b>usuario</b> para realizar a autenticação.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return usuario
	 */
	public Usuario logar(String nomeUsuario, String senha);

}
