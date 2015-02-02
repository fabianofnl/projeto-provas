package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.EquipeDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;
import br.com.sgpo.model.FuncionarioModel;
import br.com.sgpo.model.FuncionarioModelImpl;

/**
 * Classe que implementa os m�todos de gerenciamento de Funcion�rios
 * 
 * @author Roseli
 * 
 */
public class FuncionarioServiceImpl implements FuncionarioService {

	private FuncionarioModel funcionarioDao = new FuncionarioModelImpl();

	/**
	 * M�todo que consulta a lista de todos os funcion�rio do sistema
	 * 
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioDTO> listarFuncionarios() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarFuncionarios();
	}

	/**
	 * M�todo que consulta a lista de perfis do sistema
	 * 
	 * @return List<PerfilDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<PerfilDTO> listarPerfis() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarPerfis();
	}

	/**
	 * M�todo que cadastra um funcion�rio no sistema
	 * 
	 * @param funcionario
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void gravar(FuncionarioDTO funcionario) throws SQLException,
			ClassNotFoundException {
		funcionarioDao.gravar(funcionario);
	}

	/**
	 * M�todo que consulta dados do funcionario por matr�cula
	 * 
	 * @param matricula
	 * @return FuncionarioDTO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FuncionarioDTO buscarFuncionarioPorMatricula(Integer matricula)
			throws SQLException, ClassNotFoundException {
		return funcionarioDao.buscarFuncionarioPorMatricula(matricula);
	}

	/**
	 * M�todo que inativa um funcion�rio
	 * 
	 * @param matricula
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void inativar(Integer matricula) throws SQLException,
			ClassNotFoundException {
		funcionarioDao.inativar(matricula);
	}

	/**
	 * M�todo que altera os dados de um funcion�rio
	 * 
	 * @param funcionario
	 * @param matriculaAntiga
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void alterar(FuncionarioDTO funcionario, Integer matriculaAntiga)
			throws SQLException, ClassNotFoundException {
		funcionarioDao.alterar(funcionario, matriculaAntiga);
	}

	/**
	 * M�todo que consulta a lista de gerente (apenas o perfil de gerentes)
	 * 
	 * @return List<FuncionadioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioDTO> listarGerentes() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarGerentes();
	}

	/**
	 * M�todo que consulta a lista de colaboradores sem equipes associadas
	 * 
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioDTO> listarColaboradoresSemEquipes()
			throws SQLException, ClassNotFoundException {
		return funcionarioDao.listarColaboradoresSemEquipes();
	}

	/**
	 * M�todo que associa os colaboradores ao gerente, formando assim a equipe
	 * 
	 * @param matGerente
	 * @param matColaborador
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void associarEquipes(Integer matGerente, Integer[] matColaborador)
			throws SQLException, ClassNotFoundException {
		funcionarioDao.associarEquipes(matGerente, matColaborador);
	}

	/**
	 * M�todo que consulta a lista de equipes associadas
	 * 
	 * @return List<EquipeDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<EquipeDTO> listarEquipes() throws SQLException,
			ClassNotFoundException {
		List<EquipeDTO> listaEquipes = funcionarioDao.listarEquipes();
		for (EquipeDTO equipeDTO : listaEquipes) {
			equipeDTO.setListaColaboradores(funcionarioDao
					.listarColaboradorPorGerente(equipeDTO.getGerente()
							.getMatricula()));
		}
		return listaEquipes;
	}

	/**
	 * M�todo que remover o colaborador da equipe
	 * 
	 * @param matricula
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void removerColaborador(Integer matricula) throws SQLException,
			ClassNotFoundException {
		funcionarioDao.removerColaborador(matricula);
	}

	/**
	 * M�todo que consulta a lista de colaboradores cadastrados no sistema
	 * (perfil colaborador)
	 * 
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioDTO> listarColaboradores() throws SQLException,
			ClassNotFoundException {
		return funcionarioDao.listarColaboradores();
	}
}
