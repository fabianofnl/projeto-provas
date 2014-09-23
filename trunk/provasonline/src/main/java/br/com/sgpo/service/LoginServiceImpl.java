package br.com.sgpo.service;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.model.LoginDao;
import br.com.sgpo.model.LoginDaoImpl;

/**
 * Classe de implementa��o dos m�todos de autentica��o de usu�rios do sistema.
 * 
 * @author Roseli
 * 
 */
public class LoginServiceImpl implements LoginService {

	private LoginDao loginDao;

	public LoginServiceImpl() {
		loginDao = new LoginDaoImpl();
	}

	/**
	 * M�todo retorna objeto <b>Funcionario</b> para realizar a autentica��o.
	 */
	@Override
	public FuncionarioDTO logar(String nomeUsuario, String senha) {

		FuncionarioDTO funcionario = loginDao.logar(nomeUsuario, senha);

		return funcionario;
	}

}
