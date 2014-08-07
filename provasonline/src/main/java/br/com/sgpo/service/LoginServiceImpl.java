package br.com.sgpo.service;

import br.com.sgpo.dao.LoginDao;
import br.com.sgpo.dao.LoginDaoImpl;
import br.com.sgpo.model.Usuario;

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
	 * M�todo retorna objeto <b>Usuario</b> para realizar a autentica��o.
	 */
	@Override
	public Usuario logar(String nomeUsuario, String senha) {

		Usuario usuario = loginDao.logar(nomeUsuario, senha);

		return usuario;
	}

}
