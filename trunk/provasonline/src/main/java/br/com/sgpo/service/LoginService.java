package br.com.sgpo.service;

import br.com.sgpo.model.Funcionario;

/**
 * Interface de autentica��o de usu�rios do sistema.
 * 
 * @author Roseli
 * 
 */
public interface LoginService {

	/**
	 * M�todo retorna objeto <b>funcionario</b> para realizar a autentica��o.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return funcionario
	 */
	public Funcionario logar(String nomeUsuario, String senha);

}
