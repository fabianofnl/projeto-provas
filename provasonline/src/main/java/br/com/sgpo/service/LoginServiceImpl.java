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
	 * Método retorna objeto <b>Funcionario</b> para realizar a autenticação.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public FuncionarioDTO logar(String nomeUsuario, String senha)
			throws ClassNotFoundException, SQLException {

		FuncionarioDTO funcionario = loginDao.logar(nomeUsuario, senha);

		return funcionario;
	}

	@Override
	public String alterarSenha(FuncionarioDTO funcionario)
			throws ClassNotFoundException, SQLException {
		return loginDao.alterarSenha(funcionario);
	}

}
