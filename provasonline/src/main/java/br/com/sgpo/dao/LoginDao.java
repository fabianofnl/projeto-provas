package br.com.sgpo.dao;

import br.com.sgpo.model.Usuario;

/**
 * Interface de conex�o com a base de dados para autentica��o de usu�rios do
 * sistema.
 * 
 * @author Roseli
 * 
 */
public interface LoginDao {

	/**
	 * M�todo realiza conex�o com a base de dados e retorna objeto
	 * <b>usuario</b> para realizar a autentica��o.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return usuario
	 */
	public Usuario logar(String nomeUsuario, String senha);

}
