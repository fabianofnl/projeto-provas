package br.com.sgpo.service;

import java.sql.SQLException;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.model.LoginModel;
import br.com.sgpo.model.LoginModelImpl;

/**
 * Classe de implementa��o dos m�todos de autentica��o de usu�rios do sistema.
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
	 * M�todo retorna objeto <b>funcionario</b> para realizar a autentica��o.
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
	 * M�todo que altera a senha do usu�rio do sistema
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
