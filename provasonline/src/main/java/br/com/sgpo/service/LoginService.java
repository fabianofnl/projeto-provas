package br.com.sgpo.service;

import java.sql.SQLException;

import br.com.sgpo.dto.FuncionarioDTO;

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
	 * @return usuario
	 */
	public FuncionarioDTO logar(String nomeUsuario, String senha)
			throws ClassNotFoundException, SQLException;

	public String alterarSenha(FuncionarioDTO funcionario)throws ClassNotFoundException, SQLException;

}
