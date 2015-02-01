package br.com.sgpo.service;

import java.sql.SQLException;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.model.LoginModel;
import br.com.sgpo.model.LoginModelImpl;

/**
 * Classe de implementação dos métodos de autenticação de usuários do sistema.
 * 
 * @author Roseli
 * 
 */
public class LoginServiceImpl implements LoginService {

	private LoginModel loginDao;

	public LoginServiceImpl() {
		loginDao = new LoginModelImpl();
	}

	/**
	 * Método retorna objeto <b>funcionario</b> para realizar a autenticação.
	 * 
	 * @param nomeUsuario
	 * @param senha
	 * @return FuncionarioDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public FuncionarioDTO logar(String nomeUsuario, String senha)
			throws ClassNotFoundException, SQLException {
		return loginDao.logar(nomeUsuario, senha);
	}

	/**
	 * Método que altera a senha do usuário do sistema
	 * 
	 * @param funcionario
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO utilizado
	public String alterarSenha(FuncionarioDTO funcionario)
			throws ClassNotFoundException, SQLException {
		return loginDao.alterarSenha(funcionario);
	}

}
