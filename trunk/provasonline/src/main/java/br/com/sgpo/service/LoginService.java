package br.com.sgpo.service;

import br.com.sgpo.model.Usuario;

/**
 * Interface de autentica��o de usu�rios do sistema.
 * 
 * @author Roseli
 * 
 */
public interface LoginService {

	/**
	 * M�todo retorna objeto <b>usuario</b> para realizar a autentica��o.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return usuario
	 */
	public Usuario logar(String nomeUsuario, String senha);

}
