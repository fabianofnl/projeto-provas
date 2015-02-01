package br.com.sgpo.model;

import java.sql.SQLException;
import java.util.List;

import br.com.sgpo.dto.EquipeDTO;
import br.com.sgpo.dto.FuncionarioDTO;
import br.com.sgpo.dto.PerfilDTO;

public interface FuncionarioModel {

	/**
	 * Método que consulta a lista de todos os funcionário do sistema
	 * 
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public List<FuncionarioDTO> listarFuncionarios() throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que consulta a lista de perfis do sistema
	 * 
	 * @return List<PerfilDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public List<PerfilDTO> listarPerfis() throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que cadastra um funcionário no sistema
	 * 
	 * @param funcionario
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public void gravar(FuncionarioDTO funcionario) throws SQLException,
			ClassNotFoundException;

	public Integer getTotalRegistrosFuncionarios() throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que consulta dados do funcionario por matrícula
	 * 
	 * @param matricula
	 * @return FuncionarioDTO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public FuncionarioDTO buscarFuncionarioPorMatricula(Integer matricula)
			throws SQLException, ClassNotFoundException;

	/**
	 * Método que inativa um funcionário
	 * 
	 * @param matricula
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public void inativar(Integer matricula) throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que altera os dados de um funcionário
	 * 
	 * @param funcionario
	 * @param matriculaAntiga
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public void alterar(FuncionarioDTO funcionario, Integer matriculaAntiga)
			throws SQLException, ClassNotFoundException;

	/**
	 * Método que consulta a lista de gerente (apenas o perfil de gerentes)
	 * 
	 * @return List<FuncionadioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public List<FuncionarioDTO> listarGerentes() throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que consulta a lista de colaboradores sem equipes associadas
	 * 
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public List<FuncionarioDTO> listarColaboradoresSemEquipes()
			throws SQLException, ClassNotFoundException;

	/**
	 * Método que associa os colaboradores ao gerente, formando assim a equipe
	 * 
	 * @param matGerente
	 * @param matColaborador
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public void associarEquipes(Integer matGerente, Integer[] matColaborador)
			throws SQLException, ClassNotFoundException;

	/**
	 * Método que consulta a lista de equipes (apenas a lista de gerentes que
	 * forma a equipe)
	 * 
	 * @return List<EquipeDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public List<EquipeDTO> listarEquipes() throws SQLException,
			ClassNotFoundException;

	public Integer getTotalRegistrosEquipes() throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que consulta a lista d colaboradores peela matricula do gerente
	 * (equipe)
	 * 
	 * @param matricula
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public List<FuncionarioDTO> listarColaboradorPorGerente(Integer matricula)
			throws SQLException, ClassNotFoundException;

	/**
	 * Método que remover o colaborador da equipe
	 * 
	 * @param matricula
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public void removerColaborador(Integer matricula) throws SQLException,
			ClassNotFoundException;

	/**
	 * Método que consulta a lista de colaboradores cadastrados no sistema
	 * (perfil colaborador)
	 * 
	 * @return List<FuncionarioDTO>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	// TODO utilizado
	public List<FuncionarioDTO> listarColaboradores() throws SQLException,
			ClassNotFoundException;

}
