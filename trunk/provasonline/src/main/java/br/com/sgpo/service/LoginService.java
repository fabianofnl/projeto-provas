package br.com.sgpo.service;

import br.com.sgpo.model.Funcionario;

/**
 * Interface de autenticação de usuários do sistema.
 * 
 * @author Roseli
 * 
 */
public interface LoginService {

	/**
	 * Método retorna objeto <b>funcionario</b> para realizar a autenticação.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return funcionario
	 */
	public Funcionario logar(String nomeUsuario, String senha);

}
