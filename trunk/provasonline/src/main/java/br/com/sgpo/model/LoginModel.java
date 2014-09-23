package br.com.sgpo.model;

import br.com.sgpo.dto.FuncionarioDTO;

/**
 * Interface de conex�o com a base de dados para autentica��o de usu�rios do
 * sistema.
 * 
 * @author Roseli
 * 
 */
public interface LoginModel {

	/**
	 * M�todo realiza conex�o com a base de dados e retorna objeto
	 * <b>funcionario</b> para realizar a autentica��o.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return funcionario
	 */
	public FuncionarioDTO logar(String nomeUsuario, String senha);

}
