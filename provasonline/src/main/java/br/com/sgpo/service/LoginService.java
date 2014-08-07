package br.com.sgpo.service;

import br.com.sgpo.model.Usuario;

/**
 * Interface de autenticação de usuários do sistema.
 * 
 * @author Roseli
 * 
 */
public interface LoginService {

	/**
	 * Método retorna objeto <b>usuario</b> para realizar a autenticação.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return usuario
	 */
	public Usuario logar(String nomeUsuario, String senha);

}
