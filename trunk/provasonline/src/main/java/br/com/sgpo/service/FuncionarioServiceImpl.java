package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;
import br.com.sgpo.model.FuncionarioModel;
import br.com.sgpo.model.FuncionarioModelImpl;

/**
 * @author Roseli
 * 
 */
public class FuncionarioServiceImpl implements FuncionarioService {

	private FuncionarioModel funcionarioDao = new FuncionarioModelImpl();

	@Override
	public List<FuncionarioDTO> listarFuncionarios(Integer offSet,
			Integer recordPerPage) throws SQLException, ClassNotFoundException {
		return funcionarioDao.listarFuncionarios(offSet, recordPerPage);
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

	@Override
	public Integer getTotalRegistros() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.getTotalRegistros();
	}

	@Override
	public FuncionarioDTO buscarFuncionarioPorMatricula(Integer matricula)
			throws SQLException, ClassNotFoundException {
		return funcionarioDao.buscarFuncionarioPorMatricula(matricula);
	}

	@Override
	public void inativar(Integer matricula) throws SQLException,
			ClassNotFoundException {
		funcionarioDao.inativar(matricula);
	}

	@Override
	public void alterar(FuncionarioDTO funcionario, Integer matriculaAntiga)
			throws SQLException, ClassNotFoundException {
		funcionarioDao.alterar(funcionario, matriculaAntiga);
	}

	@Override
	public List<FuncionarioDTO> listarGerentes() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarGerentes();
	}

	@Override
	public List<FuncionarioDTO> listarColaboradores() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarColaboradores();
	}

}
