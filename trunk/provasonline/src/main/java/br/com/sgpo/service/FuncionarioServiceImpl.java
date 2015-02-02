package br.com.sgpo.service;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.EquipeDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;
import br.com.sgpo.model.FuncionarioModel;
import br.com.sgpo.model.FuncionarioModelImpl;

/**
 * Classe que implementa os métodos de gerenciamento de Funcionários
 * 
 * @author Roseli
 * 
 */
public class FuncionarioServiceImpl implements FuncionarioService {

	private FuncionarioModel funcionarioDao = new FuncionarioModelImpl();

	/**
	 * Método que consulta a lista de todos os funcionário do sistema
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
	 * Método que consulta a lista de perfis do sistema
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
	 * Método que cadastra um funcionário no sistema
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
	 * Método que consulta dados do funcionario por matrícula
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
	 * Método que inativa um funcionário
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
	 * Método que altera os dados de um funcionário
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
	 * Método que consulta a lista de gerente (apenas o perfil de gerentes)
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
	 * Método que consulta a lista de colaboradores sem equipes associadas
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
	 * Método que associa os colaboradores ao gerente, formando assim a equipe
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
	 * Método que consulta a lista de equipes associadas
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
	 * Método que remover o colaborador da equipe
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
	 * Método que consulta a lista de colaboradores cadastrados no sistema
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
