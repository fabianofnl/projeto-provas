package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.EquipeDTO;
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
	public List<FuncionarioDTO> listarFuncionarios() throws SQLException, ClassNotFoundException {
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

	@Override
	public Integer getTotalRegistrosFuncionarios() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.getTotalRegistrosFuncionarios();
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
	public List<FuncionarioDTO> listarColaboradoresSemEquipes() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarColaboradoresSemEquipes();
	}

	@Override
	public void associarEquipes(Integer matGerente, Integer[] matColaborador)
			throws SQLException, ClassNotFoundException {
		funcionarioDao.associarEquipes(matGerente, matColaborador);
	}

	@Override
	public List<EquipeDTO> listarEquipes()
			throws SQLException, ClassNotFoundException {
		List<EquipeDTO> listaEquipes = funcionarioDao.listarEquipes();

		// TODO verificar se não é necessario criar outra lista e add as equipes

		for (EquipeDTO equipeDTO : listaEquipes) {

			equipeDTO.setListaColaboradores(funcionarioDao
					.listarColaboradorPorGerente(equipeDTO.getGerente()
							.getMatricula()));

		}

		return listaEquipes;
	}

	@Override
	public Integer getTotalRegistrosEquipes() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.getTotalRegistrosEquipes();
	}

	@Override
	public void removerColaborador(Integer matricula) throws SQLException,
			ClassNotFoundException {
		funcionarioDao.removerColaborador(matricula);
	}

	@Override
	public List<FuncionarioDTO> listarColaboradores() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarColaboradores();
	}
}
