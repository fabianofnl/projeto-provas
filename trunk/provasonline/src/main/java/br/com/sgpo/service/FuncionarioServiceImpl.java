package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dao.FuncionarioDao;
import br.com.sgpo.dao.FuncionarioDaoImpl;
import br.com.sgpo.model.Funcionario;
import br.com.sgpo.model.Perfil;

/**
 * @author Roseli
 * 
 */
public class FuncionarioServiceImpl implements FuncionarioService {

	private FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();

	@Override
	public List<Funcionario> listarFuncionarios() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarFuncionarios();
	}

	@Override
	public List<Perfil> listarPerfis() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarPerfis();
	}

	@Override
	public void gravar(Funcionario funcionario) throws SQLException,
			ClassNotFoundException {
		funcionarioDao.gravar(funcionario);
	}

}
