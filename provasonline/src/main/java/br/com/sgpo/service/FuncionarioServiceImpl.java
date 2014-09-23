package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;
import br.com.sgpo.model.FuncionarioDao;
import br.com.sgpo.model.FuncionarioDaoImpl;

/**
 * @author Roseli
 * 
 */
public class FuncionarioServiceImpl implements FuncionarioService {

	private FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();

	@Override
	public List<FuncionarioDTO> listarFuncionarios() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarFuncionarios();
	}

	@Override
	public List<PerfilDTO> listarPerfis() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarPerfis();
	}

	@Override
	public void gravar(FuncionarioDTO funcionario) throws SQLException,
			ClassNotFoundException {
		funcionarioDao.gravar(funcionario);
	}

}
